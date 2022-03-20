package com.cetuer.parking.app.controller;

import com.cetuer.parking.app.domain.BeaconDevice;
import com.cetuer.parking.app.domain.BeaconPoint;
import com.cetuer.parking.app.service.BeaconService;
import com.cetuer.parking.common.core.domain.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 *  信标操作
 *
 * @author Cetuer
 * @date 2022/3/6 12:09
 */
@Api(tags = "信标操作")
@RestController
@RequestMapping("/beacon")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BeaconController {

    private final BeaconService beaconService;

    @ApiOperation("获取所有信标")
    @GetMapping("/list")
    public ResultData<List<BeaconDevice>> list() {
        return ResultData.success(beaconService.selectAll());
    }

    @ApiOperation("获取终点坐标")
    @GetMapping("/endPoint")
    public ResultData<BeaconPoint> endPoint() {
        return ResultData.success(beaconService.selectEndPoint());
    }
}
