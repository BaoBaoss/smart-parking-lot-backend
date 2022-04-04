package com.cetuer.parking.app.controller;

import com.cetuer.parking.app.api.domain.ParkingLot;
import com.cetuer.parking.app.service.ParkingLotService;
import com.cetuer.parking.app.service.ParkingSpaceService;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.domain.TableInfo;
import com.cetuer.parking.common.core.enums.ResultCode;
import com.cetuer.parking.common.core.exception.ServiceException;
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
    private final ParkingSpaceService parkingSpaceService;

    @ApiOperation("获得所有停车场信息")
    @GetMapping("/list")
    public ResultData<List<ParkingLot>> list() {
        return ResultData.success(parkingLotService.list());
    }

    @ApiOperation("分页查询停车场信息")
    @GetMapping("/listByPage")
    public ResultData<TableInfo<ParkingLot>> listByPage(ParkingLot parkingLot) {
        return ResultData.success(TableInfo.getInstance(parkingLotService.selectListByPage(parkingLot)));
    }

    @ApiOperation("新增停车场信息")
    @PostMapping("/add")
    public ResultData<Void> add(@RequestBody ParkingLot parkingLot) {
        parkingLotService.insert(parkingLot);
        return ResultData.success();
    }

    @ApiOperation("根据id获取停车场信息")
    @GetMapping("/getParking/{parkingId}")
    public ResultData<ParkingLot> infoById(@PathVariable("parkingId") Integer parkingId) {
        return ResultData.success(parkingLotService.selectById(parkingId));
    }

    @ApiOperation("修改停车场信息")
    @PutMapping("/update")
    public ResultData<Void> update(@RequestBody ParkingLot parkingLot) {
        parkingLotService.update(parkingLot);
        return ResultData.success();
    }

    @ApiOperation("删除停车场信息")
    @DeleteMapping("/del/{id}")
    public ResultData<Void> del(@PathVariable("id") Integer parkingId) {
        if(parkingSpaceService.hasSpace(parkingId)) {
            throw new ServiceException(ResultCode.DELETE_PARKING_HAS_SPACE_ERROR);
        }
        parkingLotService.delById(parkingId);
        return ResultData.success();
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
