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
     * 查找菜单列表
     * @param menu 菜单信息
     * @return 菜单列表
     */
    List<Menu> selectMenuList(Menu menu);

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
}
