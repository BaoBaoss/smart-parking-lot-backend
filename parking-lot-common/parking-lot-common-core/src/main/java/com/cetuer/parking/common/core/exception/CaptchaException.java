package com.cetuer.parking.common.core.exception;

/**
 * 验证码错误异常
 *
 * @author Cetuer
 * @date 2022/1/14 23:23
 */
public class CaptchaException extends RuntimeException{
    private static final long serialVersionUID = 1747961248263380998L;
    public CaptchaException(String msg) {
        super(msg);
    }
}
