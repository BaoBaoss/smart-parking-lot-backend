package com.cetuer.parking.user.controller;

import com.cetuer.parking.common.domain.ResultData;
import com.cetuer.parking.user.api.domain.User;
import com.cetuer.parking.user.api.model.LoginUser;
import com.cetuer.parking.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private final UserService userService;

    /**
     * 通过用户名查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    @ApiOperation("通过用户名查询用户信息")
    @ApiImplicitParam(value = "用户名", required = true)
    @GetMapping("/info/{username}")
    public ResultData<LoginUser> info(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        if(null == user) {
            return ResultData.fail("用户名或密码错误");
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(user);
        return ResultData.success(loginUser);
    }
}
