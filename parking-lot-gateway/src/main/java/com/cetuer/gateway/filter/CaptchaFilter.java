package com.cetuer.gateway.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cetuer.gateway.service.CaptchaService;
import com.cetuer.parking.common.enums.ResultCode;
import com.cetuer.parking.common.exception.CaptchaException;
import com.cetuer.parking.common.utils.ServletUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 验证码过滤
 *
 * @author Cetuer
 * @date 2022/1/14 22:08
 */
@Slf4j
@Component
@RefreshScope
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CaptchaFilter extends AbstractGatewayFilterFactory<Object> {
    private final static String[] VALIDATE_URL = new String[]{"/parking-auth/auth/login"};
    private final CaptchaService captchaService;

    @Value("${captcha.enabled}")
    private boolean captchaOnOff;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            //路径不包括登录或者关闭验证码不处理
            if (!StrUtil.containsAnyIgnoreCase(request.getURI().getPath(), VALIDATE_URL) || !captchaOnOff) {
                return chain.filter(exchange);
            }
            //请求方式不是POST不处理
            if (request.getMethod() != HttpMethod.POST) {
                return chain.filter(exchange);
            }
            // read & modify body
            Flux<DataBuffer> body = request.getBody();
            AtomicReference<String> bodyRef = new AtomicReference<>();
            body.subscribe(buffer -> {
                CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
                DataBufferUtils.release(buffer);
                bodyRef.set(charBuffer.toString());
            });
            try {
                JSONObject jsonObject = JSONUtil.parseObj(bodyRef.get());
                captchaService.verify(jsonObject.getStr("code"), jsonObject.getStr("uuid"));
            } catch (CaptchaException e) {
                log.info("验证码错误，信息为：{}", e.getMessage());
                return ServletUtil.webFluxResponseWriter(exchange.getResponse(), ResultCode.CAPTCHA_FAIL);
            }

            return chain.filter(exchange);
        };
    }
}

