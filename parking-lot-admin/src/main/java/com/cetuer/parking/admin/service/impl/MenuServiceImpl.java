package com.cetuer.parking.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.cetuer.parking.admin.domain.Menu;
import com.cetuer.parking.admin.domain.vo.MetaVo;
import com.cetuer.parking.admin.domain.vo.RouterVo;
import com.cetuer.parking.admin.mapper.MenuMapper;
import com.cetuer.parking.admin.service.MenuService;
import com.cetuer.parking.common.constant.MenuConstant;
import com.cetuer.parking.admin.api.domain.User;
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
    public List<Menu> selectMenuList(Menu menu) {
        return menuMapper.selectMenuList(menu);
    }

    @Override
    public List<Menu> selectMenuTreeByUserId(Integer userId) {
        List<Menu> menus;
        //超级管理员显示所有菜单
        if(User.isAdmin(userId)) {
            menus = menuMapper.selectMenuTreeAll();
        } else {
            menus = menuMapper.selectMenuTreeByUserId(userId);
        }
        List<Menu> menusByParentId = getMenusByParentId(menus, 0);
        menusByParentId.forEach(menu -> fillMenuChild(menus, menu));
        return menusByParentId;
    }


    /**
     * 根据提供的菜单列表找出所有父级菜单为parentId的菜单项
     *
     * @param menus    提供的菜单列表
     * @param parentId 父级菜单id
     * @return 找到的菜单项集合
     */
    public List<Menu> getMenusByParentId(List<Menu> menus, int parentId) {
        return menus.stream().filter(menu -> menu.getParentId() == parentId).collect(Collectors.toList());
    }

    /**
     * 递归填充菜单的子菜单
     *
     * @param menus 子菜单集合
     * @param menu  需要填充的菜单
     */
    public void fillMenuChild(List<Menu> menus, Menu menu) {
        List<Menu> childList = getChildMenu(menus, menu);
        menu.setChildren(childList);
        childList.forEach(child -> fillMenuChild(menus, child));
    }

    /**
     * 获取子菜单
     *
     * @param menus 菜单列表
     * @param menu  菜单项
     * @return 子菜单列表
     */
    public List<Menu> getChildMenu(List<Menu> menus, Menu menu) {
        return menus.stream().filter(m -> Objects.equals(m.getParentId(), menu.getMenuId())).collect(Collectors.toList());
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
            router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon()));
            List<Menu> childMenu = menu.getChildren();
            if (!childMenu.isEmpty() && MenuConstant.TYPE_DIR.equals(menu.getMenuType())) {
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
        //管理员拥有所有权限
        if(User.isAdmin(userId)) {
            perms.add("*:*:*");
        } else {
            perms.addAll(menuMapper.selectMenuPermsByUserId(userId));
        }
        return perms;
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
        if (0 == menu.getParentId() && MenuConstant.TYPE_DIR.equals(menu.getMenuType())){
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
        return menu.getParentId() != 0 && MenuConstant.TYPE_DIR.equals(menu.getMenuType());
    }
}
