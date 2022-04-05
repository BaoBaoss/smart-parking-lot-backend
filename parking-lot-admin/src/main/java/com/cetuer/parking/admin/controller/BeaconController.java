package com.cetuer.parking.admin.controller;

import com.cetuer.parking.app.api.RemoteBeaconService;
import com.cetuer.parking.app.api.domain.vo.BeaconDevice;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.domain.TableInfo;
import com.cetuer.parking.common.security.annotation.RequirePermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 信标操作
 *
 * @author zhangqb
 * @date 2022/4/4 10:55
 */
@Api(tags = "停车位操作")
@RestController
@RequestMapping("/beacon")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BeaconController {
    private final RemoteBeaconService remoteBeaconService;

    @ApiOperation("分页查询信标信息")
    @GetMapping("/listByPage")
    @RequirePermission("app:beacon:list")
    public ResultData<TableInfo<BeaconDevice>> listByPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, BeaconDevice beaconDevice) {
        return remoteBeaconService.listByPage(pageNum, pageSize, beaconDevice);
    }

    @ApiOperation("删除停车场所有信标信息")
    @DeleteMapping("/delAll/{parkingId}")
    @RequirePermission("app:beacon:remove")
    public ResultData<Void> delAll(@PathVariable("parkingId") Integer parkingId) {
        return remoteBeaconService.delAll(parkingId);
    }

    @ApiOperation("新增信标信息")
    @PostMapping("/add")
    @RequirePermission("app:beacon:add")
    public ResultData<Void> add(@RequestBody BeaconDevice beaconDevice) {
        return remoteBeaconService.add(beaconDevice);
    }

    @ApiOperation("根据id获取信标信息")
    @GetMapping("/getBeacon/{beaconId}")
    public ResultData<BeaconDevice> infoById(@PathVariable("beaconId") Integer beaconId) {
        return remoteBeaconService.infoById(beaconId);
    }

    @ApiOperation("修改信标信息")
    @PutMapping("/update")
    @RequirePermission("app:beacon:edit")
    public ResultData<Void> update(@RequestBody BeaconDevice beaconDevice) {
        return remoteBeaconService.update(beaconDevice);
    }

    @ApiOperation("删除信标")
    @DeleteMapping("/del/{ids}")
    @RequirePermission("app:space:remove")
    public ResultData<Void> del(@PathVariable("ids") Integer[] ids) {
        return remoteBeaconService.del(ids);
    }
}
