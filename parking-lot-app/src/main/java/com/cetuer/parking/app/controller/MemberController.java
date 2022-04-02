package com.cetuer.parking.app.controller;

import com.cetuer.parking.app.api.domain.vo.MemberLoginVo;
import com.cetuer.parking.app.api.model.LoginMember;
import com.cetuer.parking.app.api.domain.Member;
import com.cetuer.parking.app.service.MemberService;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.enums.ResultCode;
import com.cetuer.parking.common.core.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * 会员相关接口
 *
 * @author zhangqb
 * @date 2022/4/2 16:00
 */
@Api(tags = "会员操作")
@Validated
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberController {
    private final MemberService memberService;

    /**
     * 根据用户名查询会员信息
     *
     * @param username 用户名
     * @return 会员信息
     */
    @ApiOperation("根据用户名查询会员信息")
    @GetMapping("/infoByUsername/{username}")
    public ResultData<LoginMember> infoByUsername(@ApiParam(value = "用户名", required = true)
                                             @PathVariable("username")
                                             @NotBlank(message = "用户名不能为空")
                                                     String username) {
        Member member = memberService.selectByUsername(username);
        if (null == member) {
            return ResultData.fail(ResultCode.ACCOUNT_NOT_EXIST);
        }
        LoginMember loginMember = new LoginMember();
        loginMember.setMember(member);
        return ResultData.success(loginMember);
    }

    @ApiOperation("会员注册")
    @PostMapping("/register")
    public ResultData<Void> register(@Validated @RequestBody MemberLoginVo loginVo) {
        if(null != memberService.selectByUsername(loginVo.getUsername())) {
            throw new ServiceException(ResultCode.ACCOUNT_EXIST);
        }
        memberService.insert(loginVo);
        return ResultData.success();
    }
}
