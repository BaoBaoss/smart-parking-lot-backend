package com.cetuer.gateway.admin.config;

import com.cetuer.gateway.admin.handler.CaptchaHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * 路由配置信息
 *
 * @author Cetuer
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RouterFunctionConfiguration {
    private final CaptchaHandler captchaHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route(
                RequestPredicates.GET("/code").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                captchaHandler);
    }
}
