package com.cetuer.parking.admin.controller;

import com.cetuer.parking.app.api.RemoteMemberService;
import com.cetuer.parking.app.api.domain.Member;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.domain.TableInfo;
import com.cetuer.parking.common.security.annotation.RequirePermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 会员相关接口
 *
 * @author zhangqb
 * @date 2022/4/9 15:45
 */
@Api(tags = "会员操作")
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberController {
    private final RemoteMemberService remoteMemberService;

    /**
     * 根据条件分页查询会员列表
     *
     * @param member 查询条件
     * @return 会员列表
     */
    @ApiOperation("根据条件分页查询会员列表")
    @GetMapping("/listByPage")
    @RequirePermission("app:member:list")
    public ResultData<TableInfo<Member>> listByPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @ApiParam("查询条件") Member member) {
        return remoteMemberService.listByPage(pageNum, pageSize, member);
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
        return remoteMemberService.checkUsernameUnique(username);
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
        return remoteMemberService.info(id);
    }

    /**
     * 新增会员
     *
     * @param member 会员信息
     * @return 无
     */
    @ApiOperation("新增会员")
    @PostMapping("/add")
    @RequirePermission("app:member:add")
    public ResultData<Void> add(@RequestBody Member member) {
        return remoteMemberService.add(member);
    }

    /**
     * 根据ids列表批量删除会员
     *
     * @param ids 会员id列表
     * @return 删除个数
     */
    @ApiOperation("删除会员")
    @DeleteMapping("/{ids}")
    @RequirePermission("app:member:remove")
    public ResultData<Integer> remove(@PathVariable("ids") Integer[] ids) {
        return remoteMemberService.remove(ids);
    }

    /**
     * 修改会员
     *
     * @param member 会员信息
     * @return 无
     */
    @ApiOperation("修改会员")
    @PutMapping("/edit")
    @RequirePermission("app:member:edit")
    public ResultData<Void> edit(@RequestBody Member member) {
        return remoteMemberService.edit(member);
    }

    /**
     * 重置用户密码
     *
     * @param member id和密码
     * @return 无
     */
    @ApiOperation("重置用户密码")
    @PutMapping("/resetPwd")
    @RequirePermission("app:member:edit")
    public ResultData<Void> resetPwd(@RequestBody Member member) {
        return remoteMemberService.resetPwd(member);
    }
}
