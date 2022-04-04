package com.cetuer.parking.app.api.factory;

import com.cetuer.parking.app.api.RemoteParkingLotService;
import com.cetuer.parking.app.api.domain.ParkingLot;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.domain.TableInfo;
import com.cetuer.parking.common.core.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 停车场服务降级处理
 *
 * @author Cetuer
 * @date 2021/12/17 10:39
 */
@Component
@Slf4j
public class RemoteParkingLotFallbackFactory implements FallbackFactory<RemoteParkingLotService> {
    @Override
    public RemoteParkingLotService create(Throwable cause) {
        log.error("停车场服务调用失败:{}", cause.getMessage());
        return new RemoteParkingLotService() {
            @Override
            public ResultData<TableInfo<ParkingLot>> listByPage(Integer pageNum, Integer pageSize, ParkingLot parkingLot) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<Void> add(ParkingLot parkingLot) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<ParkingLot> infoById(Integer parkingId) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<Void> update(ParkingLot parkingLot) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<Void> del(Integer parkingId) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }
        };
    }
}
