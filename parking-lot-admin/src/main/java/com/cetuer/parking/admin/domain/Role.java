package com.cetuer.parking.admin.domain;

import com.cetuer.parking.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author Cetuer
 */
@ApiModel("角色")
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

    private static final long serialVersionUID = -8880172148284923487L;

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "角色名称")
    @NotBlank(message = "角色名不能为空")
    private String name;

    @ApiModelProperty(value = "显示顺序")
    @NotNull(message = "角色顺序不能为空")
    private Integer order;

    @ApiModelProperty(value = "是否启用：1->启用；0->停用")
    private Integer status;

    @ApiModelProperty(value = "新增或修改角色菜单id列表")
    private Set<Integer> menuIds;

    /**
     * 是否为超级管理员角色
     * @param roleId 角色ID
     * @return true->是超级管理员角色; false->不是超级管理员角色
     */
    public static boolean isAdmin(Integer roleId) {
        return getAdminId().equals(roleId);
    }

    /**
     * 获取超级管理员角色ID
     * @return 超级管理员角色ID
     */
    public static Integer getAdminId() {
        return 1;
    }
}