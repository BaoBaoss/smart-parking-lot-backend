package com.cetuer.parking.common.security.annotation;

import com.cetuer.parking.common.security.enums.PermissionLogical;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限认证
 *
 * @author Cetuer
 * @date 2022/2/7 11:08
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RequirePermission {
    /**
     * 需要的权限
     * @return 权限列表
     */
    String[] value();

    /**
     * 验证模式：AND->所有权限都需校验通过；OR->任意一个权限校验通过
     * @return 验证模式
     */
    PermissionLogical logical() default PermissionLogical.OR;
}
