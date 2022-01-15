package com.cetuer.gateway.handler;

import com.cetuer.gateway.service.CaptchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Nonnull;

/**
 * 生成验证码
 *
 * @author Cetuer
 * @date 2022/1/13 16:24
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CaptchaHandler implements HandlerFunction<ServerResponse> {
    private final CaptchaService captchaService;

    @Nonnull
    @Override
    public Mono<ServerResponse> handle(@Nonnull ServerRequest request) {
        return ServerResponse
                .status(HttpStatus.OK)
                .body(BodyInserters.fromValue(captchaService.createCaptcha()));
    }
}
