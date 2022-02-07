package com.cetuer.parking.common.security.exception;

import cn.hutool.core.util.ArrayUtil;

/**
 * 没有权限异常
 *
 * @author Cetuer
 * @date 2022/2/7 11:18
 */
public class NoPermissionException extends RuntimeException {
    private static final long serialVersionUID = -846064046505925711L;

    public NoPermissionException(String... permissions) {
        super(ArrayUtil.join(permissions, ","));
    }
}
