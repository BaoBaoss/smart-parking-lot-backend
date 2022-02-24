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

    /**
     * 根据条件分页查询菜单列表
     * @param menu 分页条件
     * @return 菜单列表
     */
    List<Menu> selectMenuListByPage(Menu menu);

    /**
     * 新增菜单
     * @param menu 菜单信息
     */
    void insertMenu(Menu menu);

    /**
     * 根据菜单编号查找菜单
     * @param menuId 菜单编号
     * @return 菜单
     */
    Menu selectMenuById(Integer menuId);

    /**
     * 修改菜单
     * @param menu 菜单
     */
    void updateMenu(Menu menu);

    /**
     * 根据id判断是否有子菜单
     * @param menuId 菜单id
     * @return true -> 有；false -> 没有
     */
    boolean hasChild(Integer menuId);

    /**
     * 根据菜单id查询是否有角色分配此菜单
     * @param menuId 菜单id
     * @return true -> 有；false -> 没有
     */
    boolean hasRole(Integer menuId);

    /**
     * 根据菜单id删除菜单
     * @param menuId 菜单id
     */
    void deleteById(Integer menuId);
}
