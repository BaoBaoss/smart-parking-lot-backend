package com.cetuer.gateway.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.cetuer.parking.common.core.constant.TokenConstants;
import com.cetuer.parking.common.core.enums.ResultCode;
import com.cetuer.parking.common.core.service.RedisService;
import com.cetuer.parking.common.core.utils.ServletUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 网关鉴权
 *
 * @author Cetuer
 * @date 2022/1/20 15:42
 */
@Slf4j
@Component
@RefreshScope
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthFilter implements GlobalFilter, Ordered {
    /**
     * 放行白名单
     */
    @Value("#{'${ignore.whites}'.split(',')}")
    private List<String> whites;
    private final RedisService redisService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        AntPathMatcher matcher = new AntPathMatcher();
        String url = request.getURI().getPath();
        //跳过白名单
        long count = whites.stream().filter(pattern -> matcher.match(pattern, url)).count();
        if(count > 0) {
            return chain.filter(exchange);
        }
        String token = getToken(request);
        if(StrUtil.isEmpty(token)) {
            return unauthorizedResponse(exchange, ResultCode.UNAUTHORIZED_TOKEN_NULL);
        }
        JWT jwt;
        try {
            jwt = JWTUtil.parseToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            return unauthorizedResponse(exchange, ResultCode.UNAUTHORIZED_TOKEN_ERROR);
        }
        String userKey = (String) jwt.getPayload(TokenConstants.USER_KEY);
        boolean isLogin = redisService.hasKey(TokenConstants.LOGIN_TOKEN_KEY + userKey);
        if(!isLogin) {
            return unauthorizedResponse(exchange, ResultCode.UNAUTHORIZED_TOKEN_EXPIRE);
        }
        Integer userId = (Integer) jwt.getPayload(TokenConstants.USER_ID);
        String username = (String) jwt.getPayload(TokenConstants.USERNAME);
        if(null == userId || StrUtil.isEmpty(username)) {
            return unauthorizedResponse(exchange, ResultCode.UNAUTHORIZED_TOKEN_ERROR);
        }
        //将用户信息放入请求头
        addHeader(request, TokenConstants.USER_KEY, userKey);
        addHeader(request, TokenConstants.USER_ID, userId.toString());
        addHeader(request, TokenConstants.USERNAME, username);
        return chain.filter(exchange.mutate().request(request.mutate().build()).build());
    }

    @Override
    public int getOrder() {
        return -2;
    }

    /**
     * 添加请求头
     * @param request 请求
     * @param name 名称
     * @param value 值
     */
    private void addHeader(ServerHttpRequest request, String name, String value) {
        if(null == value) {
            return;
        }
        try {
            request.mutate().header(name, URLEncoder.encode(value, StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.error("[header编码失败]键{}, 值：{}", name, value);
        }
    }

    /**
     * 未授权返回
     * @param exchange 请求
     * @param resultCode 错误码
     * @return Mono<Void>
     */
    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, ResultCode resultCode) {
        log.error("[鉴权异常处理]请求路径:{}, 原因:{}", exchange.getRequest().getPath(), resultCode.getMessage());
        return ServletUtil.webFluxResponseWriter(exchange.getResponse(), resultCode);
    }

    /**
     * 获取请求token
     * @param request 请求
     * @return token
     */
    private String getToken(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst(TokenConstants.AUTHENTICATION);
        //裁剪前缀
        if(null != token && token.startsWith(TokenConstants.PREFIX)) {
            token = token.replaceFirst(TokenConstants.PREFIX, StrUtil.EMPTY);
        }
        return token;
    }
}







