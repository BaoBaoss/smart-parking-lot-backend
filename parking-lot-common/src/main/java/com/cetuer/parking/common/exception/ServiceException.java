package com.cetuer.parking.common.exception;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -5447128469225521698L;

    /**
     * 错误原因
     */
    private String message;

}
