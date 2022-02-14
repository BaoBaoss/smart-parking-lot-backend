package com.cetuer.parking.common.core.enums;

import lombok.Getter;

/**
 * 状态码
 * 4开头表示错误来源于用户
 * 5开头表示错误来源于当前系统
 *
 * @author Cetuer
 * @date 2021/11/30 22:47
 */
@Getter
public enum ResultCode {

    /**
     * 操作成功
     */
    SUCCESS(20000, "操作成功"),

    /**
     * 操作失败
     */
    FAIL(99999, "操作失败"),

    /**
     * 账户已存在
     */
    ACCOUNT_EXIST(40111, "账户已存在"),

    /**
     * 角色名已存在
     */
    ROLE_NAME_EXIST(40154, "角色名已存在"),

    /**
     * 账户不存在
     */
    ACCOUNT_NOT_EXIST(40201, "账户不存在"),

    /**
     * 账户被停用
     */
    ACCOUNT_DISABLE(40202, "账户被停用"),

    /**
     * 账户密码错误
     */
    ACCOUNT_PASSWORD_ERROR(40210, "账户密码错误"),

    /**
     * 用户登录已过期
     */
    ACCOUNT_LOGIN_EXPIRE(40230, "用户登录已过期"),

    /**
     * 验证码错误
     */
    CAPTCHA_FAIL(40240, "验证码错误"),

    /**
     * 新旧密码相同
     */
    OLD_NEW_PASSWORD_EQUAL(40242, "新旧密码相同"),

    /**
     * 访问未授权
     */
    NO_PERMISSION(40301, "访问未授权"),

    /**
     * 令牌过期
     */
    UNAUTHORIZED_TOKEN_EXPIRE(40311, "令牌过期"),

    /**
     * 令牌为空
     */
    UNAUTHORIZED_TOKEN_NULL(40342, "令牌为空"),

    /**
     * 令牌错误
     */
    UNAUTHORIZED_TOKEN_ERROR(40343, "令牌错误"),

    /**
     * 参数校验错误
     */
    PARAMETER_ERROR(40400, "参数校验错误"),

    /**
     * 当前用户不可删除
     */
    CURRENT_ACCOUNT_DELETE_ERROR(40507, "当前用户不可删除"),

    /**
     * 超级管理员账户不可操作
     */
    ADMIN_ACCOUNT_OPERATION_ERROR(40508, "超级管理员账户不可操作"),

    /**
     * 用户上传文件出错
     */
    FILE_UPLOAD_ERROR(40700, "用户上传文件出错"),

    /**
     * 用户上传文件名称过长
     */
    FILE_NAME_LENGTH_ERROR(40706, "用户上传文件名称过长"),


    /**
     * 用户上传文件太大
     */
    FILE_SIZE_LIMIT_ERROR(40702, "用户上传文件太大"),

    /**
     * 文件删除失败
     */
    FILE_DELETE_ERROR(40702, "文件删除失败"),

    /**
     * 服务执行出错
     */
    SERVICE_ERROR(50001, "服务执行出错"),

    /**
     * 服务限流
     */
    SERVICE_LIMIT(50210, "服务限流"),

    /**
     * 服务降级
     */
    SERVICE_DEMOTION(50220, "服务降级"),

    /**
     * 网关服务出错
     */
    GATEWAY_ERROR(50400, "网关服务出错");

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
