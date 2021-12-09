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
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(ResultCode.SUCCESS.getCode());
        resultData.setMessage(ResultCode.SUCCESS.getMessage());
        resultData.setData(data);
        return resultData;
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
     * 返回失败结果
     *
     * @param failCode 失败状态码
     * @param appendInfo 追加错误信息
     * @param <T>  数据类型
     * @return 统一返回结果
     */
    public static <T> ResultData<T> fail(ResultCode failCode, String appendInfo) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(failCode.getCode());
        resultData.setMessage(failCode.getMessage() + (appendInfo == null ? "" : appendInfo));
        resultData.setData(null);
        return resultData;
    }
}
