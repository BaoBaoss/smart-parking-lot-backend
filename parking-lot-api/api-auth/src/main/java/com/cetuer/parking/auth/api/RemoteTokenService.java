package com.cetuer.parking.auth.api;

import com.cetuer.parking.auth.api.factory.RemoteTokenFallbackFactory;
import com.cetuer.parking.common.core.constant.ServiceNameConstants;
import com.cetuer.parking.common.core.domain.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程调用认证服务
 *
 * @author Cetuer
 * @date 2022/2/9 11:45
 */
@FeignClient(contextId = "remoteAuthService", value = ServiceNameConstants.AUTH_SERVICE, fallbackFactory = RemoteTokenFallbackFactory.class)
public interface RemoteTokenService {
    /**
     * 刷新已登录用户的信息
     * @param userKey 用户标识
     * @return 刷新结果
     */
    @GetMapping("/auth/refreshLoginUser/{userKey}")
    ResultData<Void> refreshLoginUser(@PathVariable("userKey") String userKey);
}
