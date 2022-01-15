package com.cetuer.gateway.service;

import com.cetuer.parking.common.domain.ResultData;
import com.cetuer.parking.common.exception.CaptchaException;

import java.util.Map;

/**
 * 验证码服务接口
 *
 * @author Cetuer
 * @date 2022/1/14 10:55
 */
public interface CaptchaService {

    /**
     * 创建验证码
     * @return 返回数据
     */
    ResultData<Map<String, Object>> createCaptcha();

    /**
     * 验证验证码
     * @param code 用户输入的验证码
     * @param uuid 验证码唯一标识
     * @throws CaptchaException 验证码错误抛出异常
     */
    void verify(String code, String uuid) throws CaptchaException;
}
