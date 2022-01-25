package com.cetuer.gateway.handler;

import com.cetuer.parking.common.enums.ResultCode;
import com.cetuer.parking.common.utils.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Nonnull;

/**
 * 网关统一异常处理
 *
 * @author Cetuer
 * @date 2021/12/9 11:09
 */
@Slf4j
@Order(-1)
@Configuration
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {
    @Nonnull
    @Override
    public Mono<Void> handle(ServerWebExchange exchange,@Nonnull Throwable ex) {
        ex.printStackTrace();
        ServerHttpResponse response = exchange.getResponse();
        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }

        log.error("[网关异常处理]请求路径:{},异常信息:{}", exchange.getRequest().getPath(), ex.getMessage());

        if (ex instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) ex;
            return ServletUtil.webFluxResponseWriter(response, ResultCode.GATEWAY_ERROR, responseStatusException.getStatus().toString());
        } else {
            return ServletUtil.webFluxResponseWriter(response, ResultCode.GATEWAY_ERROR);
        }
    }
}
