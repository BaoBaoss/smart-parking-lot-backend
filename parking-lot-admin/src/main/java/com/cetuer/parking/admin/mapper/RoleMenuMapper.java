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
}