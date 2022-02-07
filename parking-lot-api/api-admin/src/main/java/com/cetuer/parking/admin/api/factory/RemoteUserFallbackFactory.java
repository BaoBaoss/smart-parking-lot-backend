package com.cetuer.parking.admin.api.factory;

import com.cetuer.parking.admin.api.RemoteUserService;
import com.cetuer.parking.admin.api.model.LoginUser;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 *
 * @author Cetuer
 * @date 2021/12/17 10:39
 */
@Component
@Slf4j
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService> {
    @Override
    public RemoteUserService create(Throwable cause) {
        log.error("用户服务调用失败:{}", cause.getMessage());
        return new RemoteUserService() {
            @Override
            public ResultData<LoginUser> getUserInfo(String username) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }
        };
    }
}
