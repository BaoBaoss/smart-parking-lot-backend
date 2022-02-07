package com.cetuer.gateway.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.math.Calculator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.cetuer.gateway.service.CaptchaService;
import com.cetuer.parking.common.core.constant.CaptchaConstants;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.exception.CaptchaException;
import com.cetuer.parking.common.core.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 抽象实现验证码服务
 *
 * @author Cetuer
 * @date 2022/1/14 11:07
 */
@Service
@RefreshScope
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CaptchaServiceImpl implements CaptchaService {
    private final RedisService redisService;

    /**
     * 是否启用验证码
     */
    @Value("${captcha.enabled}")
    private boolean captchaOnOff;

    /**
     * 验证码类型 math:计算; char:字符
     */
    @Value("${captcha.type}")
    private String type;

    /**
     * 验证码宽度
     */
    private final int WIDTH = 160;

    /**
     * 验证码高度
     */
    private final int HEIGHT = 60;

    /**
     * 验证码字符数量
     */
    private final int CHAR_COUNT = 4;

    /**
     * 数据
     */
    private final Map<String, Object> data = new HashMap<>();

    /**
     * 响应
     */
    private final ResultData<Map<String, Object>> result = ResultData.success(data);

    /**
     * 扭曲干扰验证码
     */
    private final ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(WIDTH, HEIGHT, CHAR_COUNT, 0);

    /**
     * 输出流
     */
    private final FastByteArrayOutputStream os = new FastByteArrayOutputStream();

    @Override
    public ResultData<Map<String, Object>> createCaptcha() {
        reset();
        data.put("captchaOnOff", captchaOnOff);
        if(!captchaOnOff) {
            return result;
        }
        if("math".equals(type)) {
            shearCaptcha.setGenerator(new MathGenerator(1));
        }
        shearCaptcha.createCode();
        String uuid = IdUtil.simpleUUID();
        String code = shearCaptcha.getCode();
        if("math".equals(type)) {
            code = String.valueOf((int)Calculator.conversion(code));
        }
        shearCaptcha.write(os);
        redisService.set(CaptchaConstants.CAPTCHA_CODE_KEY + uuid, code, TimeUnit.MINUTES.toSeconds(CaptchaConstants.EXPIRE_TIME));
        data.put("img", Base64.encode(os.toByteArray()));
        data.put("uuid", uuid);
        os.close();
        return result;
    }

    @Override
    public void verify(String code, String uuid) throws CaptchaException {
        if(StrUtil.isEmpty(code)) {
            throw new CaptchaException("验证码不能为空");
        }
        if(StrUtil.isEmpty(uuid)) {
            throw new CaptchaException("验证码已失效");
        }
        String captcha = (String) redisService.get(CaptchaConstants.CAPTCHA_CODE_KEY + uuid);
        redisService.del(CaptchaConstants.CAPTCHA_CODE_KEY + uuid);
        if(StrUtil.isEmpty(captcha)) {
            throw new CaptchaException("验证码已过期");
        }
        if(!code.equalsIgnoreCase(captcha)) {
            throw new CaptchaException("验证码错误");
        }
    }

    private void reset() {
        shearCaptcha.setGenerator(new RandomGenerator(CHAR_COUNT));
        data.clear();
        os.reset();
    }
}
