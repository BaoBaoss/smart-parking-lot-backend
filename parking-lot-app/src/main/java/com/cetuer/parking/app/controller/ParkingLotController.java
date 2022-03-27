package com.cetuer.parking.app.controller;

import com.cetuer.parking.app.domain.ParkingLot;
import com.cetuer.parking.app.service.ParkingLotService;
import com.cetuer.parking.common.core.domain.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
