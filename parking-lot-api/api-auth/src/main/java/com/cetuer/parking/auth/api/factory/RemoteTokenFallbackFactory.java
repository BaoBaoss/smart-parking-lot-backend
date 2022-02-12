package com.cetuer.parking.auth.api.factory;

import com.cetuer.parking.auth.api.RemoteTokenService;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 认证服务降级处理
 *
 * @author Cetuer
 * @date 2022/2/9 11:47
 */
@Component
@Slf4j
public class RemoteTokenFallbackFactory implements FallbackFactory<RemoteTokenService> {
    @Override
    public RemoteTokenService create(Throwable cause) {
        log.error("认证服务调用失败:{}", cause.getMessage());
        return new RemoteTokenService() {
            @Override
            public ResultData<Void> refreshLoginUser(String userKey) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }
        };
    }
}
