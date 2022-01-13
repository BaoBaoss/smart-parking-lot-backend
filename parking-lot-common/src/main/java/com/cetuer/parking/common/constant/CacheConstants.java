package com.cetuer.parking.common.constant;

/**
 * 缓存相关常量
 *
 * @author Cetuer
 * @date 2021/12/20 8:32
 */
public class CacheConstants {
    /**
     * 过期时间12小时
     */
    public final static long EXPIRE_TIME = 60 * 12;

    /**
     * 刷新时间2小时
     */
    public final static long REFRESH_TIME = 60 * 2;

    /**
     * 权限缓存前缀
     */
    public final static String LOGIN_TOKEN_KEY = "login_tokens:";
}
