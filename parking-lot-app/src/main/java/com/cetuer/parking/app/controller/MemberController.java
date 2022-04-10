package com.cetuer.parking.app.controller;

import com.cetuer.parking.app.api.domain.Member;
import com.cetuer.parking.app.api.domain.vo.MemberLoginVo;
import com.cetuer.parking.app.api.model.LoginMember;
import com.cetuer.parking.app.service.MemberService;
import com.cetuer.parking.common.core.constant.TokenConstants;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.domain.TableInfo;
import com.cetuer.parking.common.core.enums.ResultCode;
import com.cetuer.parking.common.core.exception.ServiceException;
import com.cetuer.parking.common.security.utils.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

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
        if (null != memberService.selectByUsername(loginVo.getUsername())) {
            throw new ServiceException(ResultCode.ACCOUNT_EXIST);
        }
        memberService.insert(loginVo);
        return ResultData.success();
    }

    @ApiOperation("获取当前用户信息")
    @GetMapping("/getInfo")
    public ResultData<Member> getInfo(@RequestHeader(TokenConstants.USERNAME) String username) {
        return ResultData.success(memberService.selectByUsername(username));
    }

    /**
     * 根据条件分页查询会员列表
     *
     * @param member   查询条件
     * @return 会员列表
     */
    @ApiOperation("根据条件分页查询会员列表")
    @GetMapping("/listByPage")
    public ResultData<TableInfo<Member>> listByPage(@ApiParam("查询条件") Member member) {
        List<Member> memberList = memberService.selectListByPage(member);
        return ResultData.success(TableInfo.getInstance(memberList));
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
        return ResultData.success(memberService.selectByUsername(username) == null);
    }

    /**
     * 根据会员id获取会员信息
     *
     * @param id 会员id
     * @return 会员信息
     */
    @ApiOperation("根据用户id获取会员信息")
    @GetMapping("/{id}")
    public ResultData<Member> info(@PathVariable("id") Integer id) {
        return ResultData.success(memberService.selectByMemberId(id));
    }

    /**
     * 新增会员
     *
     * @param member 会员信息
     * @return 无
     */
    @ApiOperation("新增会员")
    @PostMapping("/add")
    public ResultData<Void> add(@RequestBody Member member) {
        if (!checkUsernameUnique(member.getUsername()).getData()) {
            throw new ServiceException(ResultCode.ACCOUNT_EXIST);
        }
        memberService.insertMember(member);
        return ResultData.success();
    }

    /**
     * 根据ids列表批量删除会员
     *
     * @param ids           会员id列表
     * @return 删除个数
     */
    @ApiOperation("删除会员")
    @DeleteMapping("/{ids}")
    public ResultData<Integer> remove(@PathVariable("ids") Integer[] ids) {
        return ResultData.success(memberService.deleteByIds(ids));
    }

    /**
     * 修改会员
     *
     * @param member 会员信息
     * @return 无
     */
    @ApiOperation("修改会员")
    @PutMapping("/edit")
    public ResultData<Void> edit(@RequestBody Member member) {
        memberService.updateMember(member);
        return ResultData.success();
    }

    /**
     * 重置用户密码
     *
     * @param member id和密码
     * @return 无
     */
    @ApiOperation("重置用户密码")
    @PutMapping("/resetPwd")
    public ResultData<Void> resetPwd(@RequestBody Member member) {
        memberService.resetPwd(member);
        return ResultData.success();
    }

    /**
     * 重置当前用户密码
     *
     * @param id id
     * @param password 密码
     * @return 无
     */
    @ApiOperation("重置当前用户密码")
    @GetMapping("/resetCurPwd")
    public ResultData<Void> resetCurPwd(@RequestHeader(TokenConstants.USER_ID) Integer id, String password) {
        memberService.resetPwd(new Member(id, password));
        return ResultData.success();
    }

    @ApiOperation("检查密码是否匹配")
    @GetMapping("/checkMatchPwd")
    public ResultData<Boolean> checkMatchPwd(@RequestHeader(TokenConstants.USERNAME) String username, String password) {
        Member member = memberService.selectByUsername(username);
        return ResultData.success(SecurityUtil.matchesPassword(password, member.getPassword()));
    }

    @ApiOperation("app端修改会员")
    @PostMapping("/updateMember")
    public ResultData<Void> updateMember(@RequestHeader(TokenConstants.USER_ID) Integer userId, @RequestBody Member member) {
        member.setId(userId);
        memberService.updateMember(member);
        return ResultData.success();
    }
}
