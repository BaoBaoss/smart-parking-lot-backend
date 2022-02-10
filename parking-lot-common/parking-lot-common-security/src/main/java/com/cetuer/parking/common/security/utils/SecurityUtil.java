package com.cetuer.parking.common.security.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.cetuer.parking.admin.api.model.LoginUser;
import com.cetuer.parking.common.core.constant.TokenConstants;
import com.cetuer.parking.common.core.service.RedisService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 安全相关工具类
 *
 * @author Cetuer
 * @date 2021/12/17 17:01
 */
public class SecurityUtil {

    /**
     * 加密密码
     *
     * @param password 原密码
     * @return 加密后的密码
     */
    public static String encryptPassword(String password) {
        if (StrUtil.isEmpty(password)) {
            return null;
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后密码
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 根据userKey获取登录用户
     *
     * @param userKey 用户唯一标识
     * @return 登录用户，如果未登录则返回null
     */
    public static LoginUser getLoginUser(String userKey) {
        return (LoginUser) SpringUtil.getBean(RedisService.class).get(TokenConstants.LOGIN_TOKEN_KEY + userKey);
    }

    /**
     * 检查用户是否已经登录
     *
     * @param userKey 用户唯一标识
     * @return true->已登录；false->未登录
     */
    public static boolean isLoginUser(String userKey) {
        return getLoginUser(userKey) != null;
    }

    public static void main(String[] args) {
        System.out.println(encryptPassword("cetuer"));
    }
}
