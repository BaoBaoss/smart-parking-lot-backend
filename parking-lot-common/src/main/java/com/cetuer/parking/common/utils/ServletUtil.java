package com.cetuer.parking.common.utils;

import com.cetuer.parking.common.domain.ResultData;
import com.cetuer.parking.common.enums.ResultCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

/**
 * 客户端工具类
 *
 * @author Cetuer
 */
@Slf4j
public class ServletUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * webflux 响应
     * @param response ServerHttpResponse
     * @param code 响应码及原因
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, ResultCode code) {
        return webFluxResponseWriter(response, code, null);
    }

    /**
     * webflux 响应
     * @param response ServerHttpResponse
     * @param code 响应码及原因
     * @param appendInfo 追加信息
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, ResultCode code, String appendInfo) {
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        ResultData<Void> result = ResultData.fail(code, appendInfo);
        String resultJson = null;
        try {
            resultJson = mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("序列化失败 ex={}", e.getMessage(), e);
            resultJson = "返回数据序列化失败！";
        }

        DataBuffer dataBuffer = response.bufferFactory().wrap(resultJson.getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }
}
