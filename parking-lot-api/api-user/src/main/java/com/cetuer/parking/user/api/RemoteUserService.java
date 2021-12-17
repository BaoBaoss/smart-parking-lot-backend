package com.cetuer.parking.user.api;

import com.cetuer.parking.common.domain.ResultData;
import com.cetuer.parking.common.constant.ServiceNameConstants;
import com.cetuer.parking.user.api.factory.RemoteUserFallbackFactory;
import com.cetuer.parking.user.api.model.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程调用用户服务
 *
 * @author Cetuer
 * @date 2021/12/17 10:31
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.USER_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService {
    /**
     * 通过用户名查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    @GetMapping("/user/info/{username}")
    ResultData<LoginUser> getUserInfo(@PathVariable("username") String username);
}
