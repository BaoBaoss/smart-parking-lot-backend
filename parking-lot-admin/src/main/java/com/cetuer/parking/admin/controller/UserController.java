package com.cetuer.parking.admin.controller;

import com.cetuer.parking.admin.api.domain.User;
import com.cetuer.parking.admin.api.model.LoginUser;
import com.cetuer.parking.admin.service.MenuService;
import com.cetuer.parking.admin.service.RoleService;
import com.cetuer.parking.admin.service.UserService;
import com.cetuer.parking.common.core.constant.TokenConstants;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.domain.TableInfo;
import com.cetuer.parking.common.core.enums.ResultCode;
import com.cetuer.parking.common.core.service.RedisService;
import com.cetuer.parking.common.security.annotation.RequirePermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户操作
 *
 * @author Cetuer
 * @date 2021/12/17 10:17
 */
@Api(tags = "用户操作")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final RedisService redisService;
    private final UserService userService;
    private final RoleService roleService;
    private final MenuService menuService;

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    @ApiOperation("根据用户名查询用户信息")
    @GetMapping("/info/{username}")
    public ResultData<LoginUser> info(@ApiParam(value = "用户名", required = true) @PathVariable("username") String username) {
        User user = userService.selectUserByUsername(username);
        if(null == user) {
            return ResultData.fail(ResultCode.ACCOUNT_NOT_EXIST);
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(user);
        loginUser.setPermissions(menuService.selectMenuPermsByUserId(user.getId()));
        loginUser.setRoles(roleService.selectRolePermsByUserId(user.getId()));
        return ResultData.success(loginUser);
    }

    /**
     * 根据条件分页查询用户列表
     * @param user 查询条件
     * @return 用户列表
     */
    @ApiOperation("根据条件分页查询用户列表")
    @GetMapping("/list")
    @RequirePermission({"system:user:list"})
    public ResultData<TableInfo<User>> listWithPage(User user) {
        List<User> userList = userService.selectUserListWithPage(user);
        return ResultData.success(TableInfo.getInstance(userList));
    }

    /**
     * 获取用户信息
     * @return 用户信息
     */
    @GetMapping("/getInfo")
    @ApiOperation("获取用户信息")
    public ResultData<Map<String, Object>> getInfo(@ApiParam(value = "用户唯一标识", required = true) @RequestHeader(TokenConstants.USER_KEY) String userKey) {
        Map<String, Object> resMap = new HashMap<>(4);
        LoginUser loginUser = (LoginUser) redisService.get(TokenConstants.LOGIN_TOKEN_KEY + userKey);
        resMap.put("user", loginUser.getUser());
        resMap.put("roles", loginUser.getRoles());
        resMap.put("permissions", loginUser.getPermissions());
        return ResultData.success(resMap);
    }
}
