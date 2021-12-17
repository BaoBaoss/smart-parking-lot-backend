package com.cetuer.parking.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cetuer.parking.common.domain.ResultData;
import com.cetuer.parking.common.enums.ResultCode;
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
public class ServletUtil {
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
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }
}
