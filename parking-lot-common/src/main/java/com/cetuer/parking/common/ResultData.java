package com.cetuer.parking.common;

import lombok.Data;

/**
 * 统一返回结果
 *
 * @author Cetuer
 * @date 2021/11/30 22:26
 */
@Data
public class ResultData<T> {
    /**
     * 状态码
     *
     * @see ResultCode
     */
    private int status;

    /**
     * 操作描述
     **/
    private String message;

    /**
     * 数据
     **/
    private T data;

    /**
     * 调用接口时间
     */
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
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(failCode.getCode());
        resultData.setMessage(failCode.getMessage());
        resultData.setData(null);
        return resultData;
    }
}
