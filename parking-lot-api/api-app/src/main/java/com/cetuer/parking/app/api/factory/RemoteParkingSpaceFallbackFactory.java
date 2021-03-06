package com.cetuer.parking.app.api.factory;

import com.cetuer.parking.app.api.RemoteParkingSpaceService;
import com.cetuer.parking.app.api.domain.ParkingSpace;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.domain.TableInfo;
import com.cetuer.parking.common.core.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 停车位服务降级处理
 *
 * @author Cetuer
 * @date 2021/12/17 10:39
 */
@Component
@Slf4j
public class RemoteParkingSpaceFallbackFactory implements FallbackFactory<RemoteParkingSpaceService> {

    @Override
    public RemoteParkingSpaceService create(Throwable cause) {
        log.error("停车位服务调用失败:{}", cause.getMessage());
        return new RemoteParkingSpaceService() {
            @Override
            public ResultData<Void> delAll(Integer parkingId) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<TableInfo<ParkingSpace>> listByPage(Integer pageNum, Integer pageSize, Integer parkingId) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<Void> add(ParkingSpace parkingSpace) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<ParkingSpace> infoById(Integer spaceId) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<Void> update(ParkingSpace parkingSpace) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<Void> del(Integer[] ids) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

        };
    }
}
