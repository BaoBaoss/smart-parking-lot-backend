package com.cetuer.parking.menu.mapper;

import com.cetuer.parking.menu.domain.Menu;

import java.util.List;

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
}
