package com.cetuer.parking.admin.controller;

import cn.hutool.core.util.ArrayUtil;
import com.cetuer.parking.admin.api.domain.User;
import com.cetuer.parking.admin.domain.Role;
import com.cetuer.parking.admin.service.RoleService;
import com.cetuer.parking.admin.service.UserService;
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
    private final UserService userService;

    /**
     * 根据当前登录用户权限获取所有角色列表，如果是超级管理员可以获得超级管理员角色，其它用户获取不到超级管理员角色
     *
     * @param userId 当前登录用户id
     * @return 角色列表
     */
    @ApiOperation("获取所有角色列表")
    @RequirePermission({"system:user:edit", "system:user:add"})
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
    @RequirePermission("system:user:edit")
    public ResultData<List<Integer>> listRoleByUserId(@PathVariable("userId") Integer userId) {
        return ResultData.success(roleService.selectRoleIdsByUserId(userId));
    }

    /**
     * 根据条件分页查询角色列表 (非管理员角色查询不到管理员角色和自己拥有的角色)
     *
     * @param role 查询条件
     * @param userId 当前登录角色
     * @return 用户列表
     */
    @ApiOperation("根据条件分页查询角色列表")
    @GetMapping("/listByPage")
    @RequirePermission("system:role:list")
    public ResultData<TableInfo<Role>> listByPage(@ApiParam("查询条件") Role role,  @RequestHeader(TokenConstants.USER_ID) Integer userId) {
        List<Role> roleList = roleService.selectRoleListByPage(role, AdminUtil.isAdminUser(userId), roleService.selectRoleIdsByUserId(userId));
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
    @RequirePermission({"system:role:add", "system:role:edit"})
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
    @RequirePermission(("system:role:edit"))
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


    /**
     * 根据角色id分页查询分配此角色用户列表
     * @param roleId 角色id
     * @param username 搜索条件：用户名
     * @param phone 搜索条件：手机号码
     * @return 分配此角色用户列表
     */
    @ApiOperation("分页查询分配此角色用户列表")
    @GetMapping("/allocatedList")
    @RequirePermission("system:role:edit")
    public ResultData<TableInfo<User>> allocatedListByPage(@RequestParam("roleId") Integer roleId, @RequestParam(value = "username", required = false) String username, @RequestParam(value = "phone", required = false) String phone, @RequestHeader(TokenConstants.USER_ID) Integer userId) {
        if(Role.isAdmin(roleId) && !AdminUtil.isAdminUser(userId)) {
            throw new ServiceException(ResultCode.ADMIN_ROLE_OPERATION_ERROR);
        }
        return ResultData.success(TableInfo.getInstance(userService.selectAllocatedListByPage(roleId, username, phone)));
    }


    /**
     * 角色批量取消授权用户
     * @param userIds 用户id列表
     * @param roleId 角色id
     * @return 无
     */
    @ApiOperation("角色取消授权用户")
    @PutMapping("/cancelAuthUser")
    @RequirePermission("system:role:edit")
    public ResultData<Void> cancelAuthUser(Integer[] userIds, Integer roleId, @RequestHeader(TokenConstants.USER_ID) Integer userId) {
        if(Role.isAdmin(roleId) && !AdminUtil.isAdminUser(userId)) {
            throw new ServiceException(ResultCode.ADMIN_ROLE_OPERATION_ERROR);
        }
        roleService.cancelAuthUser(userIds, roleId);
        return ResultData.success();
    }

    /**
     * 根据角色id分页查询未分配此角色用户列表
     * @param roleId 角色id
     * @param username 搜索条件：用户名
     * @param phone 搜索条件：手机号码
     * @return 未分配此角色用户列表
     */
    @ApiOperation("分页查询未分配此角色用户列表")
    @GetMapping("/unallocatedList")
    @RequirePermission("system:role:edit")
    public ResultData<TableInfo<User>> unallocatedListByPage(@RequestParam("roleId") Integer roleId, @RequestParam(value = "username", required = false) String username, @RequestParam(value = "phone", required = false) String phone, @RequestHeader(TokenConstants.USER_ID) Integer userId) {
        if(Role.isAdmin(roleId) && !AdminUtil.isAdminUser(userId)) {
            throw new ServiceException(ResultCode.ADMIN_ROLE_OPERATION_ERROR);
        }
        return ResultData.success(TableInfo.getInstance(userService.selectUnallocatedListByPage(roleId, username, phone)));
    }

    /**
     * 批量授权用户
     * @param roleId 角色id
     * @param userIds 用户id列表
     * @return 无
     */
    @ApiOperation("批量授权用户")
    @PutMapping("/authUsers")
    @RequirePermission("system:role:edit")
    public ResultData<Void> authUsers(Integer roleId, Integer[] userIds, @RequestHeader(TokenConstants.USER_ID) Integer userId) {
        if(Role.isAdmin(roleId) && !AdminUtil.isAdminUser(userId)) {
            throw new ServiceException(ResultCode.ADMIN_ROLE_OPERATION_ERROR);
        }
        roleService.authUsers(roleId, userIds);
        return ResultData.success();
    }
}
