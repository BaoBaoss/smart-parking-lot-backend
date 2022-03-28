package com.cetuer.parking.app.controller;

import com.cetuer.parking.app.domain.BeaconDevice;
import com.cetuer.parking.app.domain.BeaconPoint;
import com.cetuer.parking.app.service.BeaconService;
import com.cetuer.parking.common.core.domain.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

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
}
