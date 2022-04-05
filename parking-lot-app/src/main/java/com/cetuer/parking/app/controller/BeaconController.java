package com.cetuer.parking.app.controller;

import com.cetuer.parking.app.api.domain.vo.BeaconDevice;
import com.cetuer.parking.app.domain.BeaconPoint;
import com.cetuer.parking.app.service.BeaconService;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.domain.TableInfo;
import com.cetuer.parking.common.core.enums.ResultCode;
import com.cetuer.parking.common.core.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 信标操作
 *
 * @author Cetuer
 * @date 2022/3/6 12:09
 */
@Api(tags = "信标操作")
@Validated
@RestController
@RequestMapping("/beacon")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BeaconController {

    private final BeaconService beaconService;

    @ApiOperation("根据停车场编号获取其取所有信标")
    @GetMapping("/listByParkingLotId/{parkingLotId}")
    public ResultData<List<BeaconDevice>> listByParkingLotId(@ApiParam(name = "停车场编号", required = true)
                                                             @NotNull(message = "停车场编号不能为空")
                                                             @PathVariable("parkingLotId")
                                                                     Integer parkingLotId) {
        return ResultData.success(beaconService.selectDeviceByParkingLotId(parkingLotId));
    }

    @ApiOperation("根据停车场编号获取其终点坐标")
    @GetMapping("/endPointByParkingLotId/{parkingLotId}")
    public ResultData<BeaconPoint> endPointByParkingLotId(@ApiParam(name = "停车场编号", required = true)
                                                          @NotNull(message = "停车场编号不能为空")
                                                          @PathVariable("parkingLotId")
                                                                  Integer parkingLotId) {
        return ResultData.success(beaconService.selectEndPointByParkingLotId(parkingLotId));
    }

    @ApiOperation("分页查询信标信息")
    @GetMapping("/listByPage")
    public ResultData<TableInfo<BeaconDevice>> listByPage(BeaconDevice beacon) {
        return ResultData.success(TableInfo.getInstance(beaconService.selectListByPage(beacon)));
    }

    @ApiOperation("删除停车场所有信标信息")
    @DeleteMapping("/delAll/{parkingId}")
    public ResultData<Void> delAll(@PathVariable("parkingId") Integer parkingId) {
        beaconService.delAllByParkingId(parkingId);
        return ResultData.success();
    }

    @ApiOperation("新增信标信息")
    @PostMapping("/add")
    public ResultData<Void> add(@RequestBody BeaconDevice beaconDevice) {
        if(beaconService.hasBeacon(beaconDevice.getParkingLotId(), beaconDevice.getMac())) {
            throw new ServiceException(ResultCode.HAS_BEACON_ERROR);
        }
        beaconService.insert(beaconDevice);
        return ResultData.success();
    }

    @ApiOperation("根据id获取信标信息")
    @GetMapping("/getBeacon/{beaconId}")
    public ResultData<BeaconDevice> infoById(@PathVariable("beaconId") Integer beaconId) {
        return ResultData.success(beaconService.selectById(beaconId));
    }

    @ApiOperation("修改信标信息")
    @PutMapping("/update")
    public ResultData<Void> update(@RequestBody BeaconDevice beaconDevice) {
        if(beaconService.hasBeaconExcludesSelf(beaconDevice.getId(), beaconDevice.getParkingLotId(), beaconDevice.getMac())) {
            throw new ServiceException(ResultCode.HAS_BEACON_ERROR);
        }
        beaconService.update(beaconDevice);
        return ResultData.success();
    }

    @ApiOperation("删除信标")
    @DeleteMapping("/del/{ids}")
    public ResultData<Void> del(@PathVariable("ids") Integer[] ids) {
        beaconService.del(ids);
        return ResultData.success();
    }
}
