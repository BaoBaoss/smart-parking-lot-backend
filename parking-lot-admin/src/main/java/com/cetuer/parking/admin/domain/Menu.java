package com.cetuer.parking.admin.domain;

import com.cetuer.parking.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单实体类
 *
 * @author Cetuer
 * @date 2021/12/9 21:07
 */
@ApiModel("菜单实体")
@Data
@EqualsAndHashCode(callSuper = true)
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 3656835356761076055L;

    @ApiModelProperty(value = "菜单id")
    private Integer id;

    @ApiModelProperty(value = "父菜单id")
    private Integer parentId;

    @ApiModelProperty(value = "菜单名称")
    @NotBlank(message = "菜单名不能为空")
    private String name;

    @ApiModelProperty(value = "显示顺序")
    @NotNull(message = "显示顺序不能为空")
    private Integer order;

    @ApiModelProperty(value = "路由地址")
    private String routePath;

    @ApiModelProperty(value = "组件地址")
    private String componentPath;

    @ApiModelProperty(value = "菜单类型：M->目录；C->菜单；A->按钮")
    private String type;

    @ApiModelProperty(value = "是否显示：1->显示；0->隐藏")
    private Integer visible;

    @ApiModelProperty(value = "菜单状态：1->正常；0->停用")
    private Integer status;

    @ApiModelProperty(value = "权限标识")
    private String perms;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "子菜单")
    private List<Menu> children = new ArrayList<>();
}