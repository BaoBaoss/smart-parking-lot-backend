package com.cetuer.parking.common.utils;

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
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword 真实密码
     * @param encodedPassword 加密后密码
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public static void main(String[] args) {
        System.out.println(encryptPassword("admin"));
    }
}
