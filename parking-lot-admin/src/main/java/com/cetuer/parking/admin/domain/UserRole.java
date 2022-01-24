package com.cetuer.parking.admin.domain;

import com.cetuer.parking.common.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Cetuer
 */
@ApiModel("用户角色")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = 7197852432062128095L;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "角色ID")
    private Integer roleId;

}