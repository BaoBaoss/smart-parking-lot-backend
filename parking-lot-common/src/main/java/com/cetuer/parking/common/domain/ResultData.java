package com.cetuer.parking.common.domain;

import com.cetuer.parking.common.enums.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 统一返回结果
 *
 * @author Cetuer
 * @date 2021/11/30 22:26
 */
@Data
@ApiModel(description = "统一响应")
public class ResultData<T> {

    /**
     * @see ResultCode
     */
    @ApiModelProperty(value = "状态码", required = true)
    private int status;

    @ApiModelProperty(value = "操作描述", required = true)
    private String message;

    @ApiModelProperty(value = "数据", required = true)
    private T data;

    @ApiModelProperty(value = "调用接口时间", required = true)
    private long timestamp;

    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 返回成功结果
     *
     * @param data 返回数据
     * @param <T>  数据类型
     * @return 统一返回结果
     */
    public static <T> ResultData<T> success(T data) {
        return result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 返回失败结果
     *
     * @param failCode 失败状态码
     * @param <T>  数据类型
     * @return 统一返回结果
     */
    public static <T> ResultData<T> fail(ResultCode failCode) {
        return fail(failCode, null);
    }

    /**
     * 返回失败结果，使用默认失败状态
     * @param message 失败原因
     * @param <T>  数据类型
     * @return 统一返回结果
     */
    public static <T> ResultData<T> fail(String message) {
        return fail(ResultCode.FAIL.getCode(), message);
    }

    /**
     * 返回失败结果
     *
     * @param failCode 失败状态码
     * @param appendInfo 追加错误信息
     * @param <T>  数据类型
     * @return 统一返回结果
     */
    public static <T> ResultData<T> fail(ResultCode failCode, String appendInfo) {
        return fail(failCode.getCode(), failCode.getMessage() + (appendInfo == null ? "" : " " + appendInfo));
    }

    /**
     * 返回失败结果
     *
     * @param failCode 失败状态码
     * @param message 错误信息
     * @return 统一返回结果
     */
    private static <Void> ResultData<Void> fail(int failCode, String message) {
        return result(failCode, message, null);
    }

    /**
     * 返回结果
     * @param code 状态码
     * @param message 失败原因
     * @param data 数据
     * @param <T> 数据类型
     * @return 返回结果
     */
    private static <T> ResultData<T> result(int code, String message, T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(code);
        resultData.setMessage(message);
        resultData.setData(data);
        return resultData;
    }
}
