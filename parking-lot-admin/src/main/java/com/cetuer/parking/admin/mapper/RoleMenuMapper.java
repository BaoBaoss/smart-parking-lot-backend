package com.cetuer.parking.admin.mapper;

import com.cetuer.parking.admin.domain.RoleMenu;

import java.util.List;

/**
* 角色菜单数据层
* 
* @author Cetuer
* @date 2022/2/14 16:25
*/
public interface RoleMenuMapper {

    /**
     * 插入数据
     * @param roleMenuList 数据列表
     */
    void insertRoleMenu(List<RoleMenu> roleMenuList);

    /**
     * 根据角色id列表批量删除数据
     * @param roleIds 角色id列表
     */
    void deleteByRoleIds(Integer[] roleIds);

    /**
     * 根据菜单id查找角色数量
     * @param menuId 菜单id
     * @return 数量
     */
    Integer selectRoleCountByMenuId(Integer menuId);

    /**
     * 根据菜单id删除菜单
     * @param menuId 菜单id
     */
    void deleteById(Integer menuId);
}