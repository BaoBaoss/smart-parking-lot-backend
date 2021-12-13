package com.cetuer.parking.menu.service;

import com.cetuer.parking.menu.domain.Menu;
import com.cetuer.parking.menu.domain.vo.RouterVo;

import java.util.List;

/**
 * 菜单 业务层
 *
 * @author Cetuer
 * @date 2021/12/9 21:18
 */
public interface MenuService {

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
    List<Menu> selectMenuTree();

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    List<RouterVo> buildMenus(List<Menu> menus);
}
