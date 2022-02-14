package com.cetuer.parking.admin.domain;

import com.cetuer.parking.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 角色菜单关联表
 *
 * @author Cetuer
 * @date 2022/2/14 16:25
 */
@ApiModel(value = "角色菜单")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenu extends BaseEntity {
    private static final long serialVersionUID = 7779697045767732745L;
    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private Integer roleId;

    /**
     * 菜单ID
     */
    @ApiModelProperty(value = "菜单ID")
    private Integer menuId;
}