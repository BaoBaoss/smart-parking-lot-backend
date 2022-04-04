package com.cetuer.parking.app.api;


import com.cetuer.parking.app.api.factory.RemoteParkingSpaceFallbackFactory;
import com.cetuer.parking.common.core.constant.ServiceNameConstants;
import com.cetuer.parking.common.core.domain.ResultData;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程调用停车位服务
 *
 * @author Cetuer
 * @date 2021/12/17 10:31
 */
@FeignClient(contextId = "remoteParkingSpaceService", value = ServiceNameConstants.APP_SERVICE, fallbackFactory = RemoteParkingSpaceFallbackFactory.class)
public interface RemoteParkingSpaceService {
    @ApiOperation("删除停车场所有停车位信息")
    @DeleteMapping("/parkingSpace/delAll/{parkingId}")
    ResultData<Void> delAll(@PathVariable("parkingId") Integer parkingId);
}
