package com.cetuer.parking.app.api.model;

import com.cetuer.parking.app.api.domain.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 登录后的会员
 *
 * @author zhangqb
 * @date 2022/4/2 16:08
 */
@ApiModel(description = "登录会员")
@Data
public class LoginMember implements Serializable {
    private static final long serialVersionUID = -6884981451467617576L;

    /**
     * 用户唯一标识
     */
    @ApiModelProperty("用户唯一标识")
    private String uuid;

    /**
     * 登录时间
     */
    @ApiModelProperty("登录时间")
    private Long loginTime;

    /**
     * 过期时间
     */
    @ApiModelProperty("过期时间")
    private Long expireTime;

    /**
     * 会员信息
     */
    @ApiModelProperty("会员信息")
    private Member member;

}
