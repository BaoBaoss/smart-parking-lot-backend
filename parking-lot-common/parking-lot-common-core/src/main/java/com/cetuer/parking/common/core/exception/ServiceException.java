package com.cetuer.parking.common.core.exception;

import com.cetuer.parking.common.core.enums.ResultCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 业务异常
 *
 * @author Cetuer
 * @date 2021/12/17 15:39
 */
@Getter
@Setter
@NoArgsConstructor
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -5447128469225521698L;

    /**
     * 错误返回码
     */
    private ResultCode reason;

    /**
     * 错误提示
     */
    private String msg;

    public ServiceException(ResultCode reason) {
        this(reason, null);
    }

    public ServiceException(ResultCode reason, String msg) {
        this.reason = reason;
        this.msg = msg;
    }
}
