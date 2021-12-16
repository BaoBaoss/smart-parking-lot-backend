package com.cetuer.parking.common.handler;

import com.cetuer.parking.common.domain.ResultData;
import com.cetuer.parking.common.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 微服务内部错误全局异常处理
 *
 * @author Cetuer
 * @date 2021/12/16 13:50
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常处理
     * @param e 异常信息
     * @return 返回
     */
    @ExceptionHandler(Exception.class)
    public ResultData<String> exception(Exception e) {
        log.error("微服务内部全局异常 ex={}", e.getMessage(), e);
        return ResultData.fail(ResultCode.SERVICE_INNER_ERROR, e.getMessage());
    }

}
