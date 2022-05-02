package com.cetuer.parking.app.controller;

import com.cetuer.parking.app.api.domain.Car;
import com.cetuer.parking.app.service.CarService;
import com.cetuer.parking.common.core.constant.TokenConstants;
import com.cetuer.parking.common.core.domain.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 车辆表控制层
 *
 * @author zhangqb
 */
@Api(tags = "车辆操作")
@Validated
@RestController
@RequestMapping("/car")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarController {

    private final CarService carService;

    @ApiOperation("获取此用户车辆信息")
    @GetMapping("/getInfo")
    public ResultData<Car> getInfo(@RequestHeader(TokenConstants.USER_ID) Integer userId) {
        return ResultData.success(carService.getCarByUserId(userId));
    }

    @ApiOperation("添加或修改车辆信息")
    @PostMapping("/addOrUpdate")
    public ResultData<Void> addOrUpdate(@RequestHeader(TokenConstants.USER_ID) Integer userId, @RequestBody Car car) {
        carService.addOrUpdate(userId, car);
        return ResultData.success();
    }

    @ApiOperation("是否可停车")
    @GetMapping("/canParking")
    public ResultData<Boolean> canParking(@RequestHeader(TokenConstants.USER_ID) Integer userId) {
        return ResultData.success(carService.canParking(userId));
    }

    @ApiOperation("是否可寻车")
    @GetMapping("/canFindCar")
    public ResultData<Boolean> canFindCar(@RequestHeader(TokenConstants.USER_ID) Integer userId) {
        return ResultData.success(carService.canFindCar(userId));
    }
}
