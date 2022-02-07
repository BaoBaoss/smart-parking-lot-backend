package com.cetuer.parking.common.security.enums;

/**
 * 权限注解逻辑模式
 *
 * @author Cetuer
 * @date 2022/2/7 11:12
 */
public enum PermissionLogical {

    /**
     * 所有权限都通过
     */
    AND,

    /**
     * 任意一个权限通过
     */
    OR
}
