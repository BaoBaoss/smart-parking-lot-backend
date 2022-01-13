package com.cetuer.gateway.filter;

import cn.hutool.core.util.ZipUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.springframework.cloud.gateway.filter.WebClientWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER;

/**
 * TODO(后面用作响应加密，暂时没写)
 *
 * @author Cetuer
 * @date 2021/12/9 14:14
 */
@Slf4j
@Component
public class ResponseFilter implements GlobalFilter, Ordered {

    @Override
    public int getOrder() {
        // -1 is response write filter, must be called before that
        return WRITE_RESPONSE_FILTER_ORDER - 2;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("global filter HttpResponseBody，processing response results");

        // 这里可以增加一些业务判断条件，进行跳过处理

        ServerHttpResponse response = exchange.getResponse();
        DataBufferFactory bufferFactory = response.bufferFactory();
        // 响应装饰
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(response) {
            @Nonnull
            @Override
            public Mono<Void> writeWith(@Nonnull Publisher<? extends DataBuffer> body) {
                log.info("global filter HttpResponseBody，Response processing，getStatusCode={}", getStatusCode());
                if (getStatusCode() != null && body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                    return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
                        // 如果响应过大，会进行截断，出现乱码，看api DefaultDataBufferFactory
                        // 有个join方法可以合并所有的流，乱码的问题解决
                        DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                        DataBuffer dataBuffer = dataBufferFactory.join(dataBuffers);
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        // 释放掉内存
                        DataBufferUtils.release(dataBuffer);

                        List<String> encodingList = exchange.getResponse().getHeaders().get(HttpHeaders.CONTENT_ENCODING);
                        boolean zip = encodingList != null && encodingList.contains("gzip");
                        // responseData就是response的值，就可查看修改了
                        String responseData = getResponseData(zip, content);

                        // 重置返回参数
                        String result = responseConversion(responseData);
                        byte[] uppedContent = getUppedContent(zip, result);
                        response.getHeaders().setContentLength(uppedContent.length);
                        response.setStatusCode(HttpStatus.OK);

                        return bufferFactory.wrap(uppedContent);
                    }));
                }
                // if body is not a flux. never got there.
                return super.writeWith(body);
            }
        };
        // replace response with decorator
        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }

    private String responseConversion(String result) {
        try {
            log.info("响应结果为：{}", result);
            // 返回值基本数据类型、返回对象、数组的判断
            //ResultData<String> resultData = ResultData.success(result);
            return JSONUtil.toJsonStr(result);
        } catch (Exception e) {
            log.error("响应包装转换失败，异常信息为：", e);
            return result;
        }
    }

    private String getResponseData(boolean zip, byte[] content) {
        if (zip) {
            return ZipUtil.unGzip(content, StandardCharsets.UTF_8.name());
        } else {
            return new String(content, StandardCharsets.UTF_8);
        }
    }


    private byte[] getUppedContent(boolean zip, String result) {
        if (zip) {
             return ZipUtil.gzip(result, StandardCharsets.UTF_8.name());
        } else {
            return result.getBytes(StandardCharsets.UTF_8);
        }
    }
}
