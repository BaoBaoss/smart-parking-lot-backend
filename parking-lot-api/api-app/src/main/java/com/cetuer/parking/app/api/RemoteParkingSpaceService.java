package com.cetuer.parking.app.api;


import com.cetuer.parking.app.api.domain.ParkingSpace;
import com.cetuer.parking.app.api.factory.RemoteParkingSpaceFallbackFactory;
import com.cetuer.parking.common.core.constant.ServiceNameConstants;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.domain.TableInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("分页查询车位信息")
    @GetMapping("/parkingSpace/listByPage")
    ResultData<TableInfo<ParkingSpace>> listByPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestParam("parkingId") Integer parkingId);

    @ApiOperation("新增车位信息")
    @PostMapping("/parkingSpace/add")
    ResultData<Void> add(@RequestBody ParkingSpace parkingSpace);

    @ApiOperation("根据id获取车位信息")
    @GetMapping("/parkingSpace/getSpace/{spaceId}")
    ResultData<ParkingSpace> infoById(@PathVariable("spaceId") Integer spaceId);

    @ApiOperation("修改车位信息")
    @PutMapping("/parkingSpace/update")
    ResultData<Void> update(@RequestBody ParkingSpace parkingSpace);

    @ApiOperation("删除车位")
    @DeleteMapping("/parkingSpace/del/{ids}")
    ResultData<Void> del(@PathVariable("ids") Integer[] ids);
}
