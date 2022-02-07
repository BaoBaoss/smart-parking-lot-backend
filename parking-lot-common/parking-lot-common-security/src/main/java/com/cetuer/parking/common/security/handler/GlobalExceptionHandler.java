package com.cetuer.parking.common.security.handler;

import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.enums.ResultCode;
import com.cetuer.parking.common.core.exception.ServiceException;
import com.cetuer.parking.common.security.exception.NoPermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 微服务内部错误全局异常处理
 *
 * @author Cetuer
 * @date 2021/12/16 13:50
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoPermissionException.class)
    public ResultData<Void> noPermissionException(NoPermissionException e, HttpServletRequest request) {
        log.error("请求地址'{}',权限码校验失败'{}'", request.getRequestURI(), e.getMessage());
        return ResultData.fail(ResultCode.NO_PERMISSION);
    }

    /**
     * 业务异常
     * @param e 异常信息
     * @return 返回
     */
    @ExceptionHandler(ServiceException.class)
    public ResultData<Void> serviceException(ServiceException e) {
        e.printStackTrace();
        log.error("业务错误 ex={}", e.getReason().getMessage());
        return ResultData.fail(e.getReason());
    }

    /**
     * 参数校验异常
     * @param e 异常信息
     * @return 返回
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultData<String> validException(MethodArgumentNotValidException e) {
        e.printStackTrace();
        log.error("参数校验错误 ex={}", e.getMessage());
        StringBuilder sb = new StringBuilder();
        for (ObjectError error : e.getAllErrors()) {
            sb.append(error.getDefaultMessage());
            sb.append(System.lineSeparator());
        }
        return ResultData.fail(ResultCode.PARAMETER_ERROR, sb.toString());
    }

    /**
     * 全局异常处理
     * @param e 异常信息
     * @return 返回
     */
    @ExceptionHandler(Exception.class)
    public ResultData<String> globalException(Exception e) {
        e.printStackTrace();
        log.error("微服务未知错误 ex={}", e.getMessage());
        return ResultData.fail(ResultCode.SERVICE_ERROR);
    }

}
