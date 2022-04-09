package com.cetuer.parking.app.api;


import com.cetuer.parking.app.api.domain.Member;
import com.cetuer.parking.app.api.factory.RemoteMemberFallbackFactory;
import com.cetuer.parking.app.api.model.LoginMember;
import com.cetuer.parking.common.core.constant.ServiceNameConstants;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.domain.TableInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

/**
 * 远程调用会员服务
 *
 * @author Cetuer
 * @date 2021/12/17 10:31
 */
@FeignClient(contextId = "remoteMemberService", value = ServiceNameConstants.APP_SERVICE, fallbackFactory = RemoteMemberFallbackFactory.class)
public interface RemoteMemberService {
    /**
     * 通过用户名查询会员信息
     * @param username 用户名
     * @return 会员信息
     */
    @GetMapping("/member/infoByUsername/{username}")
    ResultData<LoginMember> getMemberInfo(@PathVariable("username") String username);


    /**
     * 根据条件分页查询会员列表
     *
     * @param member   查询条件
     * @return 会员列表
     */
    @ApiOperation("根据条件分页查询会员列表")
    @GetMapping("/member/listByPage")
    ResultData<TableInfo<Member>> listByPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @SpringQueryMap Member member);

    /**
     * 检查用户名是否唯一
     *
     * @param username 用户名
     * @return true->唯一；false->不唯一
     */
    @ApiOperation("检查用户名是否唯一")
    @GetMapping("/member/check/{username}")
    ResultData<Boolean> checkUsernameUnique(@PathVariable("username") String username);

    /**
     * 根据会员id获取会员信息
     *
     * @param id 会员id
     * @return 会员信息
     */
    @ApiOperation("根据用户id获取会员信息")
    @GetMapping("/member/{id}")
    ResultData<Member> info(@PathVariable("id") Integer id);

    /**
     * 新增会员
     *
     * @param member 会员信息
     * @return 无
     */
    @ApiOperation("新增会员")
    @PostMapping("/member/add")
    ResultData<Void> add(@RequestBody Member member);

    /**
     * 根据ids列表批量删除会员
     *
     * @param ids           会员id列表
     * @return 删除个数
     */
    @ApiOperation("删除会员")
    @DeleteMapping("/member/{ids}")
     ResultData<Integer> remove(@PathVariable("ids") Integer[] ids);

    /**
     * 修改会员
     *
     * @param member 会员信息
     * @return 无
     */
    @ApiOperation("修改会员")
    @PutMapping("/member/edit")
    ResultData<Void> edit(@RequestBody Member member);

    /**
     * 重置用户密码
     *
     * @param member id和密码
     * @return 无
     */
    @ApiOperation("重置用户密码")
    @PutMapping("/member/resetPwd")
    ResultData<Void> resetPwd(@RequestBody Member member);
}
