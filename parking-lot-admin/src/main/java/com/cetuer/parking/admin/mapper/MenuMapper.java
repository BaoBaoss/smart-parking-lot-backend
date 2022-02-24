package com.cetuer.parking.admin.mapper;


import com.cetuer.parking.admin.domain.Menu;

import java.util.List;
import java.util.Set;

/**
 * 菜单 数据层
 *
 * @author Cetuer
 * @date 2021/12/9 21:18
 */
public interface MenuMapper {

    /**
     * 根据用户id查找其拥有的所有菜单
     * @param userId 用户id
     * @return 菜单列表
     */
    List<Menu> selectMenuList(Integer userId);

    /**
     * 查询菜单树信息
     *
     * @return 菜单列表
     */
    List<Menu> selectMenuTreeAll();

    /**
     * 根据用户ID查找其菜单权限列表
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectMenuPermsByUserId(Integer userId);

    /**
     * 根据用户id查询菜单树信息
     *
     * @param userId 用户id
     * @return 菜单列表
     */
    List<Menu> selectMenuTreeByUserId(Integer userId);

    /**
     * 查找所有菜单
     * @return 菜单列表
     */
    List<Menu> selectMenuListAll();

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
     * 根据菜单id查找子菜单数量
     * @param menuId 菜单id
     * @return 子菜单数量
     */
    Integer selectChildCountById(Integer menuId);
}
