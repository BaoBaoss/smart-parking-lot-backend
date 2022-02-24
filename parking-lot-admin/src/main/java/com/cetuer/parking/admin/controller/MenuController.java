package com.cetuer.parking.admin.controller;

import com.cetuer.parking.admin.domain.Menu;
import com.cetuer.parking.admin.domain.Role;
import com.cetuer.parking.admin.domain.vo.RouterVo;
import com.cetuer.parking.admin.domain.vo.TreeSelect;
import com.cetuer.parking.admin.service.MenuService;
import com.cetuer.parking.common.core.constant.TokenConstants;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.enums.ResultCode;
import com.cetuer.parking.common.core.exception.ServiceException;
import com.cetuer.parking.common.security.annotation.RequirePermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 根据条件分页查询菜单列表
     * @param menu 分页条件
     * @return 菜单列表
     */
    @ApiOperation("根据条件分页查询菜单列表")
    @GetMapping("/listByPage")
    @RequirePermission("system:menu:list")
    public ResultData<List<Menu>> listByPage(Menu menu) {
        return ResultData.success(menuService.selectMenuListByPage(menu));
    }

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
     * @return 下拉树结构
     */
    @ApiOperation("查询菜单下拉树")
    @GetMapping("/treeSelect")
    @RequirePermission("system:role:add")
    public ResultData<List<TreeSelect>> treeSelect(@RequestHeader(TokenConstants.USER_ID) Integer userId) {
        List<Menu> menuList = menuService.selectMenuList(userId);
        return ResultData.success(menuService.buildMenuTreeSelect(menuList));
    }

    /**
     * 根据角色id查询前端需要的下拉树式结构
     * @param roleId 角色ID
     * @return 下拉树结构
     */
    @ApiOperation("根据角色查询其菜单下拉树")
    @GetMapping("/roleMenuTreeSelect/{roleId}")
    @RequirePermission({"system:menu:query", "system:role:edit"})
    public ResultData<Map<String, Object>> roleMenuTreeSelect(@PathVariable("roleId") Integer roleId, @RequestHeader(TokenConstants.USER_ID) Integer userId) {
        if(Role.isAdmin(roleId)) {
            throw new ServiceException(ResultCode.ADMIN_ROLE_OPERATION_ERROR);
        }
        Map<String, Object> res = new HashMap<>(2);
        List<Menu> menuList = menuService.selectMenuList(userId);
        res.put("menus", menuService.buildMenuTreeSelect(menuList));
        res.put("checkedKeys", menuService.selectMenuIdsByRoleId(roleId));
        return ResultData.success(res);
    }

    /**
     * 新增菜单
     * @param menu 菜单信息
     * @return 无
     */
    @ApiOperation("新增菜单")
    @PostMapping
    @RequirePermission("system:menu:add")
    public ResultData<Void> add(@RequestBody @Validated Menu menu) {
        menuService.insertMenu(menu);
        return ResultData.success();
    }

    /**
     * 根据菜单编号获取菜单详情
     * @param menuId 菜单编号
     * @return 菜单信息
     */
    @ApiOperation("根据菜单编号获取菜单详情")
    @GetMapping("/{menuId}")
    @RequirePermission("system:menu:edit")
    public ResultData<Menu> getInfo(@PathVariable Integer menuId) {
        return ResultData.success(menuService.selectMenuById(menuId));
    }

    /**
     * 修改菜单
     * @param menu 菜单信息
     * @return 无
     */
    @ApiOperation("修改菜单")
    @PutMapping
    @RequirePermission("system:menu:edit")
    public ResultData<Void> edit(@Validated @RequestBody Menu menu) {
        if(menu.getId().equals(menu.getParentId())) {
            throw new ServiceException(ResultCode.FAIL, "上级菜单不能选择自己");
        }
        menuService.updateMenu(menu);
        return ResultData.success();
    }

    /**
     * 删除菜单
     * @param menuId 菜单id
     * @return 无
     */
    @ApiOperation("删除菜单")
    @DeleteMapping("/{menuId}")
    @RequirePermission("system:menu:remove")
    public ResultData<Void> del(@PathVariable Integer menuId) {
        if(menuService.hasChild(menuId)) {
            throw new ServiceException(ResultCode.FAIL, "存在子菜单，无法删除");
        }
        if(menuService.hasRole(menuId)) {
            throw new ServiceException(ResultCode.FAIL, "菜单已分配，无法删除");
        }
        menuService.deleteById(menuId);
        return ResultData.success();
    }
}
