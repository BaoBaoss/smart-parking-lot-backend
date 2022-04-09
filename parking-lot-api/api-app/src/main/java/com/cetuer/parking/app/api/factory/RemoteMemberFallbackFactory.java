package com.cetuer.parking.app.api.factory;

import com.cetuer.parking.app.api.RemoteMemberService;
import com.cetuer.parking.app.api.domain.Member;
import com.cetuer.parking.app.api.model.LoginMember;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.domain.TableInfo;
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

            @Override
            public ResultData<TableInfo<Member>> listByPage(Integer pageNum, Integer pageSize, Member member) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<Boolean> checkUsernameUnique(String username) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<Member> info(Integer id) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<Void> add(Member member) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<Integer> remove(Integer[] ids) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<Void> edit(Member member) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<Void> resetPwd(Member member) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }
        };
    }
}
