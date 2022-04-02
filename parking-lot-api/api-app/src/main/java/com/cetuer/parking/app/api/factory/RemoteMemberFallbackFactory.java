package com.cetuer.parking.app.api.factory;

import com.cetuer.parking.app.api.RemoteMemberService;
import com.cetuer.parking.app.api.model.LoginMember;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 会员服务降级处理
 *
 * @author Cetuer
 * @date 2021/12/17 10:39
 */
@Component
@Slf4j
public class RemoteMemberFallbackFactory implements FallbackFactory<RemoteMemberService> {
    @Override
    public RemoteMemberService create(Throwable cause) {
        log.error("会员服务调用失败:{}", cause.getMessage());
        return new RemoteMemberService() {
            @Override
            public ResultData<LoginMember> getMemberInfo(String username) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }
        };
    }
}
