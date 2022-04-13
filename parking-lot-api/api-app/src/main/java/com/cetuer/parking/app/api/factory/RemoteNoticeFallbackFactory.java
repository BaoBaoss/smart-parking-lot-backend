package com.cetuer.parking.app.api.factory;

import com.cetuer.parking.app.api.RemoteNoticeService;
import com.cetuer.parking.app.api.domain.Notice;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.domain.TableInfo;
import com.cetuer.parking.common.core.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 公告服务降级处理
 *
 * @author Cetuer
 * @date 2021/12/17 10:39
 */
@Component
@Slf4j
public class RemoteNoticeFallbackFactory implements FallbackFactory<RemoteNoticeService> {

    @Override
    public RemoteNoticeService create(Throwable cause) {
        log.error("公告服务调用失败:{}", cause.getMessage());
        return new RemoteNoticeService() {
            @Override
            public ResultData<TableInfo<Notice>> listByPage(Integer pageNum, Integer pageSize, Integer parkingId) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<Void> add(Notice notice) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<Notice> infoById(Integer noticeId) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<Void> update(Notice notice) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<Void> del(Integer[] ids) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }
        };
    }
}
