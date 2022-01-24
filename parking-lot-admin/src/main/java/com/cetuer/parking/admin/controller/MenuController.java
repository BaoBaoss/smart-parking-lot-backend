package com.cetuer.parking.admin.controller;

import com.cetuer.parking.admin.domain.Menu;
import com.cetuer.parking.admin.domain.vo.RouterVo;
import com.cetuer.parking.admin.service.MenuService;
import com.cetuer.parking.common.domain.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统菜单信息
 *
 * @author Cetuer
 * @date 2021/11/30 22:39
 */
@Api(tags = "系统菜单")
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MenuController {

    private final MenuService menuService;

    @ApiOperation("获取菜单列表")
    @GetMapping("/list")
    public ResultData<List<Menu>> list(Menu menu) {
        List<Menu> menus = menuService.selectMenuList(menu);
        return ResultData.success(menus);
    }

    @ApiOperation("获取路由信息")
    @GetMapping("/getRouters")
    public ResultData<List<RouterVo>> getRouters() {
        List<Menu> menus = menuService.selectMenuTree();
        return ResultData.success(menuService.buildMenus(menus));
    }
}
