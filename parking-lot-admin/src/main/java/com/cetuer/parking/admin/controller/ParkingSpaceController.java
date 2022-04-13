package com.cetuer.parking.admin.controller;

import com.cetuer.parking.app.api.RemoteParkingSpaceService;
import com.cetuer.parking.app.api.domain.ParkingSpace;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.domain.TableInfo;
import com.cetuer.parking.common.security.annotation.RequirePermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 停车位操作
 *
 * @author zhangqb
 * @date 2022/4/4 10:55
 */
@Api(tags = "停车位操作")
@RestController
@RequestMapping("/parkingSpace")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ParkingSpaceController {
    private final RemoteParkingSpaceService remoteParkingSpaceService;

    @ApiOperation("删除停车场所有停车位信息")
    @DeleteMapping("/delAll/{parkingId}")
    @RequirePermission("app:space:remove")
    public ResultData<Void> delAll(@PathVariable("parkingId") Integer parkingId) {
        return remoteParkingSpaceService.delAll(parkingId);
    }

    @ApiOperation("分页查询车位信息")
    @GetMapping("/listByPage")
    @RequirePermission("app:space:list")
    public ResultData<TableInfo<ParkingSpace>> listByPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "parkingId", required = false) Integer parkingId) {
        return remoteParkingSpaceService.listByPage(pageNum, pageSize, parkingId);
    }

    @ApiOperation("新增车位信息")
    @PostMapping("/add")
    @RequirePermission("app:space:add")
    public ResultData<Void> add(@RequestBody ParkingSpace parkingSpace) {
        return remoteParkingSpaceService.add(parkingSpace);
    }

    @ApiOperation("根据id获取车位信息")
    @GetMapping("/getSpace/{spaceId}")
    public ResultData<ParkingSpace> infoById(@PathVariable("spaceId") Integer spaceId) {
        return remoteParkingSpaceService.infoById(spaceId);
    }

    @ApiOperation("修改车位信息")
    @PutMapping("/update")
    @RequirePermission("app:space:edit")
    public ResultData<Void> update(@RequestBody ParkingSpace parkingSpace) {
        return remoteParkingSpaceService.update(parkingSpace);
    }

    @ApiOperation("删除车位")
    @DeleteMapping("/del/{ids}")
    @RequirePermission("app:space:remove")
    public ResultData<Void> del(@PathVariable("ids") Integer[] ids) {
        return remoteParkingSpaceService.del(ids);
    }
}
