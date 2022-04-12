package com.cetuer.parking.app.api;


import com.cetuer.parking.app.api.domain.ParkingLot;
import com.cetuer.parking.app.api.factory.RemoteParkingLotFallbackFactory;
import com.cetuer.parking.common.core.constant.ServiceNameConstants;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.domain.TableInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 远程调用停车场服务
 *
 * @author Cetuer
 * @date 2021/12/17 10:31
 */
@FeignClient(contextId = "remoteParkingLotService", value = ServiceNameConstants.APP_SERVICE, fallbackFactory = RemoteParkingLotFallbackFactory.class)
public interface RemoteParkingLotService {

    @ApiOperation("查询所有停车场信息")
    @GetMapping("/parkingLot/list")
    ResultData<List<ParkingLot>> list();

    @ApiOperation("分页查询所有停车场信息")
    @GetMapping("/parkingLot/listByPage")
    ResultData<TableInfo<ParkingLot>> listByPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @SpringQueryMap ParkingLot parkingLot);

    @ApiOperation("新增停车场信息")
    @PostMapping("/parkingLot/add")
    ResultData<Void> add(@RequestBody ParkingLot parkingLot);

    @ApiOperation("根据id获取停车场信息")
    @GetMapping("/parkingLot/getParking/{parkingId}")
    ResultData<ParkingLot> infoById(@PathVariable("parkingId") Integer parkingId);

    @ApiOperation("修改停车场信息")
    @PutMapping("/parkingLot/update")
    ResultData<Void> update(@RequestBody ParkingLot parkingLot);

    @ApiOperation("删除停车场信息")
    @DeleteMapping("/parkingLot/del/{id}")
    ResultData<Void> del(@PathVariable("id") Integer parkingId);
}
