package com.cetuer.gateway.handler;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cetuer.parking.common.core.enums.ResultCode;
import com.cetuer.parking.common.core.utils.ServletUtil;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import javax.annotation.Nonnull;

/**
 * 自定义限流异常处理
 *
 * @author Cetuer
 * @date 2021/12/8 19:12
 */
public class SentinelFallbackHandler implements WebExceptionHandler {

    @Nonnull
    @Override
    public Mono<Void> handle(ServerWebExchange exchange,@Nonnull Throwable ex) {
        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }
        if (!BlockException.isBlockException(ex)) {
            return Mono.error(ex);
        }
        return GatewayCallbackManager
                .getBlockHandler()
                .handleRequest(exchange, ex)
                .flatMap(response -> ServletUtil.webFluxResponseWriter(exchange.getResponse(), ResultCode.SERVICE_LIMIT));
    }
}
