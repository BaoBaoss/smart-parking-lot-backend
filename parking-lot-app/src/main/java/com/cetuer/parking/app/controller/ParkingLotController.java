package com.cetuer.parking.app.controller;

import com.cetuer.parking.app.domain.ParkingLot;
import com.cetuer.parking.app.service.ParkingLotService;
import com.cetuer.parking.common.core.domain.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

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

    private final ParkingLotService parkingLotService;

    @ApiOperation("获得所有停车场信息")
    @GetMapping("/list")
    public ResultData<List<ParkingLot>> list() {
        return ResultData.success(parkingLotService.list());
    }

    @ApiOperation("根据经纬度获取停车场id")
    @GetMapping("/parkingIdByLatLng")
    public ResultData<Integer> parkingIdByLatLng(@ApiParam(name = "停车场经度", required = true)
                                                 @NotNull(message = "停车场经度不能为空")
                                                 @RequestParam("longitude")
                                                         Double longitude,
                                                 @ApiParam(name = "停车场纬度", required = true)
                                                 @NotNull(message = "停车场纬度不能为空")
                                                 @RequestParam("latitude")
                                                         Double latitude) {
        return ResultData.success(parkingLotService.parkingIdByLatLng(longitude, latitude));
    }
}
