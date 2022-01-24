package com.cetuer.parking.auth.controller;

import com.cetuer.parking.auth.domain.vo.LoginVo;
import com.cetuer.parking.auth.service.LoginService;
import com.cetuer.parking.auth.service.TokenService;
import com.cetuer.parking.common.domain.ResultData;
import com.cetuer.parking.admin.api.model.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
