package com.cetuer.parking.admin.controller;

import com.cetuer.parking.admin.domain.Menu;
import com.cetuer.parking.admin.domain.vo.RouterVo;
import com.cetuer.parking.admin.domain.vo.TreeSelect;
import com.cetuer.parking.admin.service.MenuService;
import com.cetuer.parking.common.core.constant.TokenConstants;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.security.annotation.RequirePermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //@ApiOperation("获取菜单列表")
    //@GetMapping("/list")
    //public ResultData<List<Menu>> list(Menu menu) {
    //    List<Menu> menus = menuService.selectMenuList(menu);
    //    return ResultData.success(menus);
    //}

    /**
     * 获取菜单路由信息
     * @param userId 用户id
     * @return 路由信息
     */
    @ApiOperation("获取路由信息")
    @GetMapping("/getRouters")
    public ResultData<List<RouterVo>> getRouters(@ApiParam(value = "用户id", required = true) @RequestHeader(TokenConstants.USER_ID) Integer userId) {
        List<Menu> menus = menuService.selectMenuTreeByUserId(userId);
        return ResultData.success(menuService.buildMenus(menus));
    }

    /**
     * 根据用户查询前端需要的下拉树式结构
     * @param userId 用户ID
     * @return 下拉树结构
     */
    @ApiOperation("查询菜单下拉树")
    @GetMapping("/treeSelect")
    @RequirePermission({"system:role:query", "system:role:add"})
    public ResultData<List<TreeSelect>> treeSelect(@RequestHeader(TokenConstants.USER_ID) Integer userId) {
        List<Menu> menuList = menuService.selectMenuList(userId);
        return ResultData.success(menuService.buildMenuTreeSelect(menuList));
    }
}
