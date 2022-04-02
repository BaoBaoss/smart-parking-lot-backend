package com.cetuer.parking.app.api;


import com.cetuer.parking.app.api.factory.RemoteMemberFallbackFactory;
import com.cetuer.parking.app.api.model.LoginMember;
import com.cetuer.parking.common.core.constant.ServiceNameConstants;
import com.cetuer.parking.common.core.domain.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
