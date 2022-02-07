package com.cetuer.parking.common.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import com.cetuer.parking.common.domain.ResultData;
import com.cetuer.parking.common.enums.ResultCode;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;

/**
 * 客户端工具类
 *
 * @author Cetuer
 */
public class ServletUtil {

    /**
     * webflux 响应
     *
     * @param response ServerHttpResponse
     * @param code     响应码及原因
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, ResultCode code) {
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        ResultData<Void> result = ResultData.fail(code);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONUtil.toJsonStr(result).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }

    /**
     * 获取String参数
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name) {
        return Convert.toInt(getRequest().getParameter(name));
    }


    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }
}
