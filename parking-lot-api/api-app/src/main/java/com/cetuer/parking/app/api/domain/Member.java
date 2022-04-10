package com.cetuer.parking.app.api.domain;

import com.cetuer.parking.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会员实体
 * @TableName member
 */
@ApiModel(value="会员实体")
@Data
@EqualsAndHashCode(callSuper=true)
public class Member extends BaseEntity {

    private static final long serialVersionUID = -6596799178022358734L;

    public Member() {
    }

    public Member(Integer id, String password) {
        this.id = id;
        this.password = password;
    }

    /**
     * 主键
     */
    @ApiModelProperty(value="主键")
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty(value="用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value="密码")
    private String password;

    /**
     * 昵称
     */
    @ApiModelProperty(value="昵称")
    private String nickname;

    /**
     * 手机号码
     */
    @ApiModelProperty(value="手机号码")
    private String phone;

    /**
     * 账号启用状态：0->禁用；1->启用
     */
    @ApiModelProperty(value="账号启用状态：0->禁用；1->启用")
    private Integer status;

    /**
     * 头像
     */
    @ApiModelProperty(value="头像")
    private String avatar;

    /**
     * 性别：0->未知；1->男；2->女
     */
    @ApiModelProperty(value="性别：0->未知；1->男；2->女")
    private Integer gender;

}