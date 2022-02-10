package com.cetuer.parking.admin.api.domain;

import com.cetuer.parking.common.core.constant.UserConstant;
import com.cetuer.parking.common.core.domain.BaseEntity;
import com.cetuer.parking.common.core.validation.UserGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Cetuer
 */
@ApiModel("用户实体")
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    private static final long serialVersionUID = -8010885284781230134L;

    @ApiModelProperty(value = "主键")
    @NotNull(message = "用户id不能为空", groups = UserGroup.ResetPwdGroup.class)
    private Integer id;

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空", groups = UserGroup.AddGroup.class)
    @Length(min = UserConstant.USERNAME_MIN_LENGTH, max = UserConstant.USERNAME_MAX_LENGTH, message = "用户名长度应在[" + UserConstant.USERNAME_MIN_LENGTH + "," + UserConstant.USERNAME_MAX_LENGTH + "]之间", groups = UserGroup.AddGroup.class)
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空", groups = {UserGroup.AddGroup.class, UserGroup.ResetPwdGroup.class})
    @Length(min = UserConstant.PASSWORD_MIN_LENGTH, max = UserConstant.PASSWORD_MAX_LENGTH, message = "密码长度应在[" + UserConstant.PASSWORD_MIN_LENGTH + "," + UserConstant.PASSWORD_MAX_LENGTH + "]之间", groups = UserGroup.AddGroup.class)
    private String password;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "昵称")
    @NotBlank(message = "昵称不能为空", groups = UserGroup.AddGroup.class)
    private String nickname;

    @ApiModelProperty(value = "备注信息")
    private String note;

    @ApiModelProperty(value = "账号启用状态：0->禁用；1->启用")
    private Integer status;

    @ApiModelProperty(value = "新增或修改用户角色id列表")
    private Integer[] roleIds;
}