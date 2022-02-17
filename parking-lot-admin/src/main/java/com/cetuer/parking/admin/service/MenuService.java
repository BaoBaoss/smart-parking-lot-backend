package com.cetuer.parking.admin.service;


import com.cetuer.parking.admin.domain.Menu;
import com.cetuer.parking.admin.domain.vo.RouterVo;
import com.cetuer.parking.admin.domain.vo.TreeSelect;

import java.util.List;
import java.util.Set;


/**
 * 菜单 业务层
 *
 * @author Cetuer
 * @date 2021/12/9 21:18
 */
public interface MenuService {

    /**
     * 根据用户id查找其拥有的菜单列表
     * @param userId 用户id
     * @return 菜单列表
     */
    List<Menu> selectMenuList(Integer userId);

    /**
     * 查找所有菜单
     * @return 菜单列表
     */
    List<Menu> selectMenuListAll();

    /**
     * 根据用户id查询菜单树信息
     *
     * @param userId 用户id
     * @return 菜单列表
     */
    List<Menu> selectMenuTreeByUserId(Integer userId);

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    List<RouterVo> buildMenus(List<Menu> menus);

    /**
     * 根据用户ID查找其菜单权限列表
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectMenuPermsByUserId(Integer userId);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param menuList 菜单列表
     * @return 下拉树结构列表
     */
    List<TreeSelect> buildMenuTreeSelect(List<Menu> menuList);

    /**
     * 根据角色id查找其拥有的菜单id列表
     * @param roleId 角色id
     * @return 菜单id列表
     */
    List<Integer> selectMenuIdsByRoleId(Integer roleId);
}
