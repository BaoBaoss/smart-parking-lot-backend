package com.cetuer.parking.app.api.domain.vo;

import com.cetuer.parking.common.core.constant.UserConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * app登录注册Vo
 *
 * @author Cetuer
 * @date 2021/12/17 9:57
 */
@ApiModel(description = "会员登录注册")
@Data
public class MemberLoginVo {
    @ApiModelProperty(required = true, value = "用户名")
    @NotBlank(message = "用户名不能为空")
    @Length(min = UserConstant.USERNAME_MIN_LENGTH, max = UserConstant.USERNAME_MAX_LENGTH, message = "用户名长度应在[" + UserConstant.USERNAME_MIN_LENGTH + "," + UserConstant.USERNAME_MAX_LENGTH + "]之间")
    private String username;

    @ApiModelProperty(required = true, value = "密码")
    @NotBlank(message = "密码不能为空")
    @Length(min = UserConstant.PASSWORD_MIN_LENGTH, max = UserConstant.PASSWORD_MAX_LENGTH, message = "密码长度应在[" + UserConstant.PASSWORD_MIN_LENGTH + "," + UserConstant.PASSWORD_MAX_LENGTH + "]之间")
    private String password;
}
