package com.cetuer.parking.admin.api.model;

import com.cetuer.parking.admin.api.domain.User;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 登录后的用户
 *
 * @author Cetuer
 * @date 2021/12/17 10:22
 */
@Data
public class LoginUser implements Serializable {
    private static final long serialVersionUID = -515837847725941267L;

    /**
     * 用户唯一标识
     */
    private String uuid;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 角色列表
     */
    private Set<String> roles;

    /**
     * 用户信息
     */
    private User user;
}
