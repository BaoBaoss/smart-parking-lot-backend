package com.cetuer.parking.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.cetuer.parking.admin.domain.Menu;
import com.cetuer.parking.admin.domain.vo.MetaVo;
import com.cetuer.parking.admin.domain.vo.RouterVo;
import com.cetuer.parking.admin.domain.vo.TreeSelect;
import com.cetuer.parking.admin.mapper.MenuMapper;
import com.cetuer.parking.admin.service.MenuService;
import com.cetuer.parking.admin.util.AdminUtil;
import com.cetuer.parking.common.core.constant.MenuConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 菜单 业务实现层
 *
 * @author Cetuer
 * @date 2021/12/9 21:19
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MenuServiceImpl implements MenuService {
    private final MenuMapper menuMapper;

    @Override
    public List<Menu> selectMenuList(Integer userId) {
        if (AdminUtil.isAdminUser(userId)) {
            return menuMapper.selectMenuListAll();
        }
        return menuMapper.selectMenuList(userId);
    }

    @Override
    public List<Menu> selectMenuTreeByUserId(Integer userId) {
        List<Menu> menus;
        //超级管理员显示所有菜单
        if (AdminUtil.isAdminUser(userId)) {
            menus = menuMapper.selectMenuTreeAll();
        } else {
            menus = menuMapper.selectMenuTreeByUserId(userId);
        }
        Map<Boolean, List<Menu>> groupMenus = getChildMenu(menus, 0);
        List<Menu> topMenus = groupMenus.get(true);
        topMenus.forEach(menu -> fillMenuChild(groupMenus.get(false), menu));
        return topMenus;
    }

    /**
     * 递归填充菜单的子菜单
     *
     * @param menus 子菜单集合
     * @param menu  需要填充的菜单
     */
    public void fillMenuChild(List<Menu> menus, Menu menu) {
        Map<Boolean, List<Menu>> groupMenus = getChildMenu(menus, menu.getId());
        List<Menu> childList = groupMenus.get(true);
        menu.setChildren(childList);
        childList.forEach(child -> fillMenuChild(groupMenus.get(false), child));
    }

    /**
     * 根据父菜单ID获取其子菜单
     *
     * @param menus        菜单列表
     * @param parentMenuId 父菜单ID
     * @return Map.get(true)->子菜单列表 Map.get(false)->除子菜单列表以外的集合
     */
    public Map<Boolean, List<Menu>> getChildMenu(List<Menu> menus, Integer parentMenuId) {
        return menus.stream().collect(Collectors.partitioningBy(m -> Objects.equals(m.getParentId(), parentMenuId)));
    }

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    @Override
    public List<RouterVo> buildMenus(List<Menu> menus) {
        List<RouterVo> routers = new ArrayList<>();
        for (Menu menu : menus) {
            RouterVo router = new RouterVo();
            router.setHidden(menu.getVisible() == 0);
            router.setName(StrUtil.upperFirst(menu.getRoutePath()));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setMeta(new MetaVo(menu.getName(), menu.getIcon()));
            List<Menu> childMenu = menu.getChildren();
            if (!childMenu.isEmpty() && MenuConstant.TYPE_DIR.equals(menu.getType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(childMenu));
            }
            routers.add(router);
        }
        return routers;
    }

    @Override
    public Set<String> selectMenuPermsByUserId(Integer userId) {
        Set<String> perms = new HashSet<>();
        if (AdminUtil.hasAllPermission(userId)) {
            perms.add("*:*:*");
        } else {
            perms.addAll(menuMapper.selectMenuPermsByUserId(userId));
        }
        return perms;
    }

    @Override
    public List<TreeSelect> buildMenuTreeSelect(List<Menu> menuList) {
        Map<Boolean, List<Menu>> groupMenus = getChildMenu(menuList, 0);
        List<Menu> topMenus = groupMenus.get(true);
        topMenus.forEach(m -> fillMenuChild(groupMenus.get(false), m));
        return topMenus.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(Menu menu) {
        String routerPath = menu.getRoutePath();
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentId() && MenuConstant.TYPE_DIR.equals(menu.getType())) {
            routerPath = "/" + menu.getRoutePath();
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(Menu menu) {
        String component = MenuConstant.LAYOUT;
        if (StrUtil.isNotEmpty(menu.getComponentPath())) {
            component = menu.getComponentPath();
        } else if (isParentView(menu)) {
            component = MenuConstant.PARENT_VIEW;
        }
        return component;
    }

    /**
     * 是否为ParentView组件
     *
     * @param menu 菜单信息
     * @return true->是; false->否
     */
    public boolean isParentView(Menu menu) {
        return menu.getParentId() != 0 && MenuConstant.TYPE_DIR.equals(menu.getType());
    }
}
