package com.cetuer.gateway.admin.config;

import com.cetuer.gateway.admin.handler.SentinelFallbackHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * 网关限流配置
 *
 * @author Cetuer
 * @date 2021/12/8 19:06
 */
@Configuration
public class GatewayConfig {

    /**
     * 配置限流的异常处理器
     *
     * @return 异常处理器
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelFallbackHandler sentinelGatewayExceptionHandler() {
        return new SentinelFallbackHandler();
    }
}
