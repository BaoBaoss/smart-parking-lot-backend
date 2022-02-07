package com.cetuer.parking.admin.api.domain;

import com.cetuer.parking.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Cetuer
 */
@ApiModel("用户实体")
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    private static final long serialVersionUID = -8010885284781230134L;

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "备注信息")
    private String note;

    @ApiModelProperty(value = "账号启用状态：0->禁用；1->启用")
    private Integer status;

    /**
     * 是否为超级管理员
     * @param userId 用户id
     * @return true 是; false 否
     */
    public static boolean isAdmin(Integer userId) {
        return userId != null && 1 == userId;
    }
}