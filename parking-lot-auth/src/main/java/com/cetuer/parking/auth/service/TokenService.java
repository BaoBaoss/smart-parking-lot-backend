package com.cetuer.parking.auth.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.cetuer.parking.common.constant.TokenConstants;
import com.cetuer.parking.common.service.RedisService;
import com.cetuer.parking.user.api.model.LoginUser;
import lombok.RequiredArgsConstructor;
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
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenService {

    private final RedisService redisService;

    /**
     * 创建令牌
     * @param loginUser 用户信息
     * @return token
     */
    public Map<String, Object> createToken(LoginUser loginUser) {
        String uuid = IdUtil.fastUUID();
        loginUser.setUuid(uuid);
        refreshToken(loginUser);

        //Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put(TokenConstants.USER_KEY, uuid);
        claimsMap.put(TokenConstants.USER_ID, loginUser.getUser().getId());
        claimsMap.put(TokenConstants.USERNAME, loginUser.getUser().getUsername());
        // 接口返回信息
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("access_token", JWTUtil.createToken(claimsMap, JWTSignerUtil.hs512(TokenConstants.SECRET.getBytes())));
        resMap.put("expires_in", loginUser.getExpireTime());

        return resMap;
    }


    /**
     * 刷新令牌
     * @param loginUser 用戶信息
     */
    public void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + TimeUnit.MINUTES.toMillis(TokenConstants.EXPIRE_TIME));
        //缓存loginUser
        redisService.set(TokenConstants.LOGIN_TOKEN_KEY + loginUser.getUuid(), loginUser, TimeUnit.MINUTES.toSeconds(TokenConstants.EXPIRE_TIME));
    }
}
