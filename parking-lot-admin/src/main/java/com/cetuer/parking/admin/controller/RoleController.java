package com.cetuer.parking.admin.controller;

import cn.hutool.core.util.ArrayUtil;
import com.cetuer.parking.admin.domain.Role;
import com.cetuer.parking.admin.service.RoleService;
import com.cetuer.parking.admin.util.AdminUtil;
import com.cetuer.parking.common.core.constant.TokenConstants;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.domain.TableInfo;
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

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色操作
 *
 * @author Cetuer
 * @date 2022/2/8 14:58
 */
@Api(tags = "角色操作")
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleController {
    private final RoleService roleService;

    /**
     * 根据当前登录用户权限获取所有角色列表，如果是超级管理员可以获得超级管理员角色，其它用户获取不到超级管理员角色
     *
     * @param userId 当前登录用户id
     * @return 角色列表
     */
    @ApiOperation("获取所有角色列表")
    @RequirePermission({"system:role:query", "system:user:edit", "system:user:add"})
    @GetMapping("/list")
    public ResultData<List<Role>> list(@RequestHeader(TokenConstants.USER_ID) Integer userId) {
        List<Role> roles = roleService.selectRoleAll();
        return ResultData.success(AdminUtil.hasAllPermission(userId) ? roles : roles.stream().filter(r -> !Role.isAdmin(r.getId())).collect(Collectors.toList()));
    }

    /**
     * 根据用户id获取其拥有的角色列表id
     *
     * @param userId 用户id
     * @return 角色列表id
     */
    @ApiOperation("根据用户id获取其拥有的角色列表id")
    @GetMapping({"/list/{userId}"})
    @RequirePermission({"system:role:query", "system:user:edit"})
    public ResultData<List<Integer>> listRoleByUserId(@PathVariable("userId") Integer userId) {
        return ResultData.success(roleService.selectRoleIdsByUserId(userId));
    }

    /**
     * 根据条件分页查询角色列表
     *
     * @param role 查询条件
     * @return 用户列表
     */
    @ApiOperation("根据条件分页查询角色列表")
    @GetMapping("/listByPage")
    @RequirePermission({"system:role:list"})
    public ResultData<TableInfo<Role>> listByPage(@ApiParam("查询条件") Role role) {
        List<Role> roleList = roleService.selectRoleListByPage(role);
        return ResultData.success(TableInfo.getInstance(roleList));
    }

    /**
     * 检查角色名是否唯一
     *
     * @param roleName 角色名
     * @return 不唯一则返回其角色名，唯一则返回null
     */
    @ApiOperation("检查角色名是否唯一")
    @GetMapping("/check/{roleName}")
    public ResultData<String> checkRoleNameUnique(@PathVariable("roleName") String roleName) {
        Role role = roleService.selectRoleByRoleName(roleName);
        return ResultData.success(role == null ? null : role.getName());
    }

    /**
     * 新增角色
     *
     * @param role 角色
     * @return 无
     */
    @ApiOperation("新增角色")
    @PostMapping
    @RequirePermission("system:role:add")
    public ResultData<Void> add(@Validated @RequestBody Role role) {
        if (checkRoleNameUnique(role.getName()).getData() != null) {
            throw new ServiceException(ResultCode.ROLE_NAME_EXIST);
        }
        roleService.insertRole(role);
        return ResultData.success();
    }

    /**
     * 根据角色ID查询角色
     *
     * @param roleId 角色ID
     * @return 角色
     */
    @ApiOperation("根据角色ID查询角色")
    @GetMapping("/{roleId}")
    @RequirePermission(("system:role:query"))
    public ResultData<Role> getInfo(@PathVariable Integer roleId) {
        return ResultData.success(roleService.selectRoleByRoleId(roleId));
    }

    /**
     * 修改角色
     *
     * @param role 角色
     * @return 无
     */
    @ApiOperation("修改角色")
    @PutMapping
    @RequirePermission("system:role:edit")
    public ResultData<Void> edit(@Validated @RequestBody Role role) {
        if (Role.isAdmin(role.getId())) {
            throw new ServiceException(ResultCode.ADMIN_ROLE_OPERATION_ERROR);
        }
        String existRoleName = checkRoleNameUnique(role.getName()).getData();
        //已存在的角色名不和此角色原本角色名相等
        if (existRoleName != null && !existRoleName.equals(roleService.selectRoleByRoleId(role.getId()).getName())) {
            throw new ServiceException(ResultCode.ROLE_NAME_EXIST);
        }
        roleService.updateRole(role);
        return ResultData.success();
    }


    /**
     * 根据角色id批量删除角色
     * @param roleIds 角色id列表
     * @return 无
     */
    @ApiOperation("删除角色")
    @DeleteMapping("/{roleIds}")
    @RequirePermission("system:role:remove")
    public ResultData<Void> remove(@PathVariable("roleIds") Integer[] roleIds) {
        if(ArrayUtil.contains(roleIds, Role.getAdminId())) {
            throw new ServiceException(ResultCode.ADMIN_ROLE_OPERATION_ERROR);
        }
        roleService.deleteByRoleIds(roleIds);
        return ResultData.success();
    }
}
