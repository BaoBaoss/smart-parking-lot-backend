package com.cetuer.parking.auth.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.cetuer.parking.admin.api.model.LoginUser;
import com.cetuer.parking.common.core.constant.TokenConstants;
import com.cetuer.parking.common.core.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 令牌 业务层
 *
 * @author Cetuer
 * @date 2021/12/19 10:27
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenService {

    private final RedisService redisService;

    /**
     * 创建令牌
     *
     * @param loginUser 用户信息
     * @return token
     */
    public Map<String, Object> createToken(LoginUser loginUser) {
        String uuid = IdUtil.fastUUID();
        loginUser.setUuid(uuid);
        refreshToken(loginUser);

        //Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<>(4);
        claimsMap.put(TokenConstants.USER_KEY, uuid);
        claimsMap.put(TokenConstants.USER_ID, loginUser.getUser().getId());
        claimsMap.put(TokenConstants.USERNAME, loginUser.getUser().getUsername());
        // 接口返回信息
        Map<String, Object> resMap = new HashMap<>(2);
        resMap.put("access_token", JWTUtil.createToken(claimsMap, JWTSignerUtil.hs512(TokenConstants.SECRET.getBytes())));
        resMap.put("expires_in", loginUser.getExpireTime());

        return resMap;
    }


    /**
     * 刷新令牌
     *
     * @param loginUser 用戶信息
     */
    public void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + TimeUnit.MINUTES.toMillis(TokenConstants.EXPIRE_TIME));
        //缓存loginUser
        redisService.set(TokenConstants.LOGIN_TOKEN_KEY + loginUser.getUuid(), loginUser, TimeUnit.MINUTES.toSeconds(TokenConstants.EXPIRE_TIME));
    }

    /**
     * 登出
     *
     * @param token 令牌
     */
    public void logout(String token) {
        //裁剪前缀
        if (null != token && token.startsWith(TokenConstants.PREFIX)) {
            token = token.replaceFirst(TokenConstants.PREFIX, StrUtil.EMPTY);
            try {
                String userKey = (String) JWTUtil.parseToken(token).getPayload(TokenConstants.USER_KEY);
                redisService.del(TokenConstants.LOGIN_TOKEN_KEY + userKey);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[token解析错误] token:{}", token);
            }
        }
    }
}
