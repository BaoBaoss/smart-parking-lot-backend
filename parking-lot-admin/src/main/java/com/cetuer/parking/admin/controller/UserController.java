package com.cetuer.parking.admin.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.cetuer.parking.admin.api.domain.User;
import com.cetuer.parking.admin.api.model.LoginUser;
import com.cetuer.parking.admin.service.MenuService;
import com.cetuer.parking.admin.service.RoleService;
import com.cetuer.parking.admin.service.UserService;
import com.cetuer.parking.admin.util.AdminUtil;
import com.cetuer.parking.auth.api.RemoteTokenService;
import com.cetuer.parking.common.core.constant.TokenConstants;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.domain.TableInfo;
import com.cetuer.parking.common.core.enums.ResultCode;
import com.cetuer.parking.common.core.exception.ServiceException;
import com.cetuer.parking.common.core.validation.UserGroup;
import com.cetuer.parking.common.security.annotation.RequirePermission;
import com.cetuer.parking.common.security.utils.SecurityUtil;
import com.cetuer.parking.file.api.RemoteFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * 用户操作
 *
 * @author Cetuer
 * @date 2021/12/17 10:17
 */
@Validated
@Api(tags = "用户操作")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final MenuService menuService;
    private final RemoteTokenService remoteTokenService;
    private final RemoteFileService remoteFileService;

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    @ApiOperation("根据用户名查询用户信息")
    @GetMapping("/info/{username}")
    public ResultData<LoginUser> info(@ApiParam(value = "用户名", required = true) @PathVariable("username") String username) {
        User user = userService.selectUserByUsername(username);
        if (null == user) {
            return ResultData.fail(ResultCode.ACCOUNT_NOT_EXIST);
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(user);
        loginUser.setPermissions(menuService.selectMenuPermsByUserId(user.getId()));
        loginUser.setRoles(roleService.selectRolePermsByUserId(user.getId()));
        return ResultData.success(loginUser);
    }

    /**
     * 检查用户名是否唯一
     *
     * @param username 用户名
     * @return true->唯一；false->不唯一
     */
    @ApiOperation("检查用户名是否唯一")
    @GetMapping("/check/{username}")
    public ResultData<Boolean> checkUsernameUnique(@PathVariable("username") String username) {
        return ResultData.success(userService.selectUserByUsername(username) == null);
    }

    /**
     * 根据用户id获取用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    @ApiOperation("根据用户id获取用户信息")
    @GetMapping("/{id}")
    @RequirePermission({"system:user:query", "system:user:edit"})
    public ResultData<User> info(@PathVariable("id") Integer id, @RequestHeader(TokenConstants.USER_ID) Integer userId) {
        //只有登录用户为超级管理员才能获取超级管理员信息
        if (AdminUtil.isAdminUser(id) && !AdminUtil.isAdminUser(userId)) {
            throw new ServiceException(ResultCode.ADMIN_ACCOUNT_OPERATION_ERROR);
        }
        return ResultData.success(userService.selectUserByUserId(id));
    }


    /**
     * 根据条件分页查询用户列表（非管理员角色查询不到管理员用户）
     *
     * @param user 查询条件
     * @param userId 当前用户id
     * @return 用户列表
     */
    @ApiOperation("根据条件分页查询用户列表")
    @GetMapping("/listByPage")
    @RequirePermission({"system:user:list"})
    public ResultData<TableInfo<User>> listByPage(@ApiParam("查询条件") User user, @RequestHeader(TokenConstants.USER_ID) Integer userId) {
        List<User> userList = userService.selectUserListByPage(user, AdminUtil.hasAllPermission(userId));
        return ResultData.success(TableInfo.getInstance(userList));
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/getInfo")
    @ApiOperation("获取用户信息")
    public ResultData<Map<String, Object>> getInfo(@ApiParam(value = "用户唯一标识", required = true) @RequestHeader(TokenConstants.USER_KEY) String userKey) {
        Map<String, Object> resMap = new HashMap<>(4);
        remoteTokenService.refreshLoginUser(userKey);
        LoginUser loginUser = SecurityUtil.getLoginUser(userKey);
        resMap.put("user", loginUser.getUser());
        resMap.put("roles", loginUser.getRoles());
        resMap.put("permissions", loginUser.getPermissions());
        return ResultData.success(resMap);
    }

    /**
     * 新增用户
     *
     * @param user 用户信息
     * @return 无
     */
    @ApiOperation("新增用户")
    @PostMapping("/add")
    @RequirePermission("system:user:add")
    public ResultData<Void> add(@Validated(UserGroup.AddGroup.class) @RequestBody User user) {
        if (!checkUsernameUnique(user.getUsername()).getData()) {
            throw new ServiceException(ResultCode.ACCOUNT_EXIST);
        }
        userService.insertUser(user);
        return ResultData.success();
    }

    /**
     * 根据ids列表批量删除用户
     *
     * @param ids           用户id列表
     * @param currentUserId 当前登录用户id
     * @return 删除个数
     */
    @ApiOperation("删除用户")
    @DeleteMapping("/{ids}")
    @RequirePermission("system:user:remove")
    public ResultData<Integer> remove(@PathVariable("ids") Integer[] ids, @RequestHeader(TokenConstants.USER_ID) Integer currentUserId) {
        if (ArrayUtil.contains(ids, currentUserId)) {
            throw new ServiceException(ResultCode.CURRENT_ACCOUNT_DELETE_ERROR);
        }
        if (ArrayUtil.contains(ids, AdminUtil.getAdminUserId())) {
            throw new ServiceException(ResultCode.ADMIN_ACCOUNT_OPERATION_ERROR);
        }
        return ResultData.success(userService.deleteByIds(ids));
    }

    /**
     * 修改用户
     *
     * @param user 用户信息
     * @return 无
     */
    @ApiOperation("修改用户")
    @PutMapping("/edit")
    @RequirePermission("system:user:edit")
    public ResultData<Void> edit(@Validated(UserGroup.AddGroup.class) @RequestBody User user, @RequestHeader(TokenConstants.USER_ID) Integer currentUserId) {
        if (AdminUtil.isAdminUser(user.getId()) && !AdminUtil.isAdminUser(currentUserId)) {
            throw new ServiceException(ResultCode.ADMIN_ACCOUNT_OPERATION_ERROR);
        }
        userService.updateUser(user);
        return ResultData.success();
    }

    /**
     * 重置用户密码
     *
     * @param user id和密码
     * @return 无
     */
    @ApiOperation("重置用户密码")
    @PutMapping("/resetPwd")
    @RequirePermission("system:user:edit")
    public ResultData<Void> resetPwd(@Validated(UserGroup.ResetPwdGroup.class) @RequestBody User user, @RequestHeader(TokenConstants.USER_ID) Integer currentUserId) {
        if (AdminUtil.isAdminUser(user.getId()) && !AdminUtil.isAdminUser(currentUserId)) {
            throw new ServiceException(ResultCode.ADMIN_ACCOUNT_OPERATION_ERROR);
        }
        userService.resetPwd(user);
        return ResultData.success();
    }

    /**
     * 用户添加角色
     *
     * @param userId  用户
     * @param roleIds 角色
     * @return 无
     */
    @ApiOperation("用户添加角色")
    @PutMapping("/updateUserRole")
    @RequirePermission("system:user:edit")
    public ResultData<Void> updateUserRole(@Valid @NotNull(message = "用户id不能为空") Integer userId, Set<Integer> roleIds) {
        userService.updateUserRole(userId, roleIds);
        return ResultData.success();
    }

    /**
     * 获取个人信息
     * @param userId 用户唯一标识
     * @return 个人信息
     */
    @ApiOperation("获取个人信息")
    @GetMapping("/profileInfo")
    public ResultData<Map<String, Object>> profileInfo(@RequestHeader(TokenConstants.USER_ID) Integer userId) {
        Map<String, Object> res = new HashMap<>(2);
        res.put("user", userService.selectUserByUserId(userId));
        res.put("roleGroup", roleService.selectRoleGroupByUserId(userId));
        return ResultData.success(res);
    }

    /**
     * 修改个人信息
     * @param user 个人信息
     * @return 无
     */
    @ApiOperation("修改个人信息")
    @PutMapping("/updateProfile")
    public ResultData<Void> updateProfile(@RequestBody User user, @RequestHeader(TokenConstants.USER_KEY) String userKey) {
        User reliableUser = SecurityUtil.getLoginUser(userKey).getUser();
        user.setId(reliableUser.getId());
        user.setUsername(reliableUser.getUsername());
        user.setPassword(null);
        user.setRoleIds(new HashSet<>(roleService.selectRoleIdsByUserId(user.getId())));
        userService.updateUser(user);
        remoteTokenService.refreshLoginUser(userKey);
        return ResultData.success();
    }

    /**
     * 修改个人密码
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @param userKey 用户标识
     * @return 无
     */
    @ApiOperation("修改个人密码")
    @PutMapping("/updateProfilePwd")
    public ResultData<Void> updateProfilePwd(String oldPwd, String newPwd, @RequestHeader(TokenConstants.USER_KEY) String userKey) {
        User user = SecurityUtil.getLoginUser(userKey).getUser();
        if(!SecurityUtil.matchesPassword(oldPwd, user.getPassword())) {
            throw new ServiceException(ResultCode.ACCOUNT_PASSWORD_ERROR);
        }
        if(SecurityUtil.matchesPassword(newPwd, user.getPassword())) {
            throw new ServiceException(ResultCode.OLD_NEW_PASSWORD_EQUAL);
        }
        user.setPassword(newPwd);
        userService.resetPwd(user);
        remoteTokenService.refreshLoginUser(userKey);
        return ResultData.success();
    }

    /**
     * 头像上传
     * @param avatarFile 头像文件
     * @return 访问地址
     */
    @ApiOperation("头像上传")
    @PostMapping("/avatar")
    public ResultData<String> avatar(MultipartFile avatarFile, @RequestHeader(TokenConstants.USER_KEY) String userKey) {
        if(avatarFile.isEmpty()) {
            throw new ServiceException(ResultCode.FILE_UPLOAD_ERROR);
        }
        User user = SecurityUtil.getLoginUser(userKey).getUser();
        String imgUrl = remoteFileService.upload(avatarFile).getData();
        if(!StrUtil.isEmpty(user.getAvatar())) {
            remoteFileService.delete(user.getAvatar());
        }
        userService.updateAvatar(user.getId(), imgUrl);
        remoteTokenService.refreshLoginUser(userKey);
        return ResultData.success(imgUrl);
    }
}
