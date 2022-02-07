package com.cetuer.parking.admin.domain;

import com.cetuer.parking.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Cetuer
 */
@ApiModel("角色菜单")
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleMenu extends BaseEntity {

    private static final long serialVersionUID = 7779697045767732745L;

    @ApiModelProperty(value = "角色ID")
    private Integer roleId;

    @ApiModelProperty(value = "菜单ID")
    private Integer menuId;
}