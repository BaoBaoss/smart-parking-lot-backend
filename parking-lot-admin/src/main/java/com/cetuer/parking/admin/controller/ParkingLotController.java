package com.cetuer.parking.admin.controller;

import com.cetuer.parking.app.api.RemoteParkingLotService;
import com.cetuer.parking.app.api.domain.ParkingLot;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.domain.TableInfo;
import com.cetuer.parking.common.security.annotation.RequirePermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 停车场相关接口
 *
 * @author zhangqb
 * @date 2022/3/26 23:34
 */
@Api(tags = "停车场操作")
@RestController
@RequestMapping("/parkingLot")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ParkingLotController {
    private final RemoteParkingLotService remoteParkingLotService;

    @ApiOperation("获得所有停车场信息")
    @GetMapping("/listByPage")
    @RequirePermission("app:parking:list")
    public ResultData<TableInfo<ParkingLot>> listByPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, ParkingLot parkingLot) {
        return remoteParkingLotService.listByPage(pageNum, pageSize, parkingLot);
    }

    @ApiOperation("新增停车场信息")
    @PostMapping("/add")
    @RequirePermission("app:parking:add")
    public ResultData<Void> add(@RequestBody ParkingLot parkingLot) {
        return remoteParkingLotService.add(parkingLot);
    }

    @ApiOperation("根据id获取停车场信息")
    @GetMapping("/getParking/{parkingId}")
    public ResultData<ParkingLot> infoById(@PathVariable("parkingId") Integer parkingId) {
        return remoteParkingLotService.infoById(parkingId);
    }

    @ApiOperation("修改停车场信息")
    @PutMapping("/update")
    @RequirePermission("app:parking:edit")
    public ResultData<Void> update(@RequestBody ParkingLot parkingLot) {
        return remoteParkingLotService.update(parkingLot);
    }

    @ApiOperation("删除停车场信息")
    @DeleteMapping("/del/{id}")
    @RequirePermission("app:parking:remove")
    public ResultData<Void> del(@PathVariable("id") Integer parkingId) {
        return remoteParkingLotService.del(parkingId);
    }
}
