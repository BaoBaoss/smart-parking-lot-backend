package com.cetuer.parking.auth.controller;

import com.cetuer.parking.admin.api.model.LoginUser;
import com.cetuer.parking.app.api.domain.vo.MemberLoginVo;
import com.cetuer.parking.app.api.model.LoginMember;
import com.cetuer.parking.auth.domain.vo.LoginVo;
import com.cetuer.parking.auth.service.LoginService;
import com.cetuer.parking.auth.service.TokenService;
import com.cetuer.parking.common.core.constant.TokenConstants;
import com.cetuer.parking.common.core.domain.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * token 控制
 *
 * @author Cetuer
 * @date 2021/12/16 18:49
 */
@Api(tags = "token认证接口")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenController {

    private final LoginService loginService;
    private final TokenService tokenService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResultData<Map<String, Object>> login(@Validated @RequestBody LoginVo login) {
        LoginUser user = loginService.login(login.getUsername(), login.getPassword());
        return ResultData.success(tokenService.createToken(user));
    }

    @ApiOperation("app登录")
    @PostMapping("/appLogin")
    public ResultData<String> appLogin(@Validated @RequestBody MemberLoginVo login) {
        LoginMember member = loginService.appLogin(login.getUsername(), login.getPassword());
        return ResultData.success(tokenService.createAppToken(member));
    }

    @ApiOperation("登出")
    @DeleteMapping("/logout")
    public ResultData<Void> logout(@ApiParam(value = "令牌", required = true) @RequestHeader(value = TokenConstants.AUTHENTICATION) String token) {
        tokenService.logout(token);
        return ResultData.success();
    }

    @ApiOperation(("刷新已登录用户信息"))
    @GetMapping("/refreshLoginUser/{userKey}")
    public ResultData<Void> refreshLoginUser(@PathVariable("userKey") String userKey) {
        tokenService.refreshLoginUser(userKey);
        return ResultData.success();
    }
}
