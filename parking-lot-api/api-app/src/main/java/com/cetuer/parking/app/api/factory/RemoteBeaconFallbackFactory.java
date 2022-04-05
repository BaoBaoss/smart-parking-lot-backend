package com.cetuer.parking.app.api.factory;

import com.cetuer.parking.app.api.RemoteBeaconService;
import com.cetuer.parking.app.api.domain.vo.BeaconDevice;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.domain.TableInfo;
import com.cetuer.parking.common.core.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 信标服务降级处理
 *
 * @author Cetuer
 * @date 2021/12/17 10:39
 */
@Component
@Slf4j
public class RemoteBeaconFallbackFactory implements FallbackFactory<RemoteBeaconService> {

    @Override
    public RemoteBeaconService create(Throwable cause) {
        return new RemoteBeaconService() {
            @Override
            public ResultData<TableInfo<BeaconDevice>> listByPage(Integer pageNum, Integer pageSize, BeaconDevice beaconDevice) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<Void> delAll(Integer parkingId) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<Void> add(BeaconDevice beaconDevice) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<BeaconDevice> infoById(Integer beaconId) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<Void> update(BeaconDevice beaconDevice) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<Void> del(Integer[] ids) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }
        };
    }
}
