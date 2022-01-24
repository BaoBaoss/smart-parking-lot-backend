package com.cetuer.parking.auth.domain.vo;

import com.cetuer.parking.common.constant.UserConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 界面登录Vo
 *
 * @author Cetuer
 * @date 2021/12/17 9:57
 */
@ApiModel("界面登录实体")
@Data
public class LoginVo {
    @ApiModelProperty(required = true, value = "用户名")
    @NotBlank(message = "用户名不能为空")
    @Length(min = UserConstant.USERNAME_MIN_LENGTH, max = UserConstant.USERNAME_MAX_LENGTH, message = "用户名不在指定范围")
    private String username;

    @ApiModelProperty(required = true, value = "密码")
    @NotBlank(message = "密码不能为空")
    @Length(min = UserConstant.PASSWORD_MIN_LENGTH, max = UserConstant.PASSWORD_MAX_LENGTH, message = "密码不在指定范围")
    private String password;
}
