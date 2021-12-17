package com.cetuer.parking.common.enums;

import lombok.Getter;

/**
 * 状态码
 *
 * @author Cetuer
 * @date 2021/11/30 22:47
 */
@Getter
public enum ResultCode {

    SUCCESS(2000, "操作成功"),

    FAIL(5999, "操作失败"),

    SERVICE_INNER_ERROR(5000, "微服务内部错误"),

    SERVICE_IS_RATE_LIMIT(5001, "服务限流"),

    GATEWAY_ERROR(5002, "网关错误"),

    SERVICE_DEMOTION(5003, "服务降级");
    /**
     * 自定义状态码
     **/
    private final int code;

    /**
     * 自定义描述
     **/
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
