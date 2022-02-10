package com.cetuer.parking.admin.controller;

import com.cetuer.parking.admin.domain.Role;
import com.cetuer.parking.admin.service.RoleService;
import com.cetuer.parking.admin.util.AdminUtil;
import com.cetuer.parking.common.core.constant.TokenConstants;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.security.annotation.RequirePermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
}
