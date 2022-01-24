package com.cetuer.parking.admin.domain;

import com.cetuer.parking.common.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "菜单id")
    private Integer menuId;

    @ApiModelProperty(value = "父菜单id")
    private Integer parentId;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "显示顺序")
    private Integer orderNum;

    @ApiModelProperty(value = "路由地址")
    private String routePath;

    @ApiModelProperty(value = "组件地址")
    private String componentPath;

    @ApiModelProperty(value = "菜单类型：M->目录；C->菜单；A->按钮")
    private String menuType;

    @ApiModelProperty(value = "是否显示：1->显示；0->隐藏")
    private Integer visible;

    @ApiModelProperty(value = "权限标识")
    private String perms;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "子菜单")
    private List<Menu> children = new ArrayList<>();
}