package com.cetuer.parking.common.constant;

/**
 * Token的Key常量
 * 
 * @author ruoyi
 */
public class TokenConstants {
    /**
     * 过期时间12小时
     */
    public final static long EXPIRE_TIME = 60 * 12;

    /**
     * 刷新时间2小时
     */
    public final static long REFRESH_TIME = 60 * 2;
    /**
     * 令牌自定义标识
     */
    public static final String AUTHENTICATION = "Authorization";

    /**
     * 令牌前缀
     */
    public static final String PREFIX = "Bearer ";

    /**
     * 令牌秘钥
     */
    public final static String SECRET = "abcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * 用户标识
     */
    public static final String USER_KEY = "user_key";

    /**
     * 登录用户
     */
    public static final String LOGIN_USER = "login_user";

    /**
     * 用户ID字段
     */
    public static final String USER_ID = "user_id";

    /**
     * 用户名字段
     */
    public static final String USERNAME = "username";

    /**
     * 权限缓存前缀
     */
    public final static String LOGIN_TOKEN_KEY = "login_tokens:";
}
