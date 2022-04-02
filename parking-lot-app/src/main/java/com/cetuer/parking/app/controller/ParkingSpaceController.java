package com.cetuer.parking.app.controller;

import com.cetuer.parking.app.domain.ParkingSpace;
import com.cetuer.parking.app.service.ParkingSpaceService;
import com.cetuer.parking.common.core.domain.ResultData;
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
 * 停车位相关操作
 *
 * @author zhangqb
 * @date 2022/3/31 14:57
 */
@Api(tags = "停车位操作")
@Validated
@RestController
@RequestMapping("/parkingSpace")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ParkingSpaceController {
    private final ParkingSpaceService parkingSpaceService;

    @ApiOperation("根据停车场编号获取其所有车位")
    @GetMapping("/listByParkingId/{parkingLotId}")
    public ResultData<List<ParkingSpace>> listByParkingId(@ApiParam(name = "停车场编号", required = true)
                                                          @NotNull(message = "停车场编号不能为空")
                                                          @PathVariable("parkingLotId")
                                                                  Integer parkingLotId) {
        return ResultData.success(parkingSpaceService.listByParkingId(parkingLotId));
    }

    /**
     * 根据停车场编号和车位坐标更改车位状态
     *
     * @param parkingId 停车场编号
     * @param x         车位x坐标
     * @param y         车位y坐标
     * @param status    要更改的状态
     * @return 无
     */
    @GetMapping("/changeStatus")
    public ResultData<Void> changeStatus(@ApiParam(name = "停车场编号", required = true)
                                              @NotNull(message = "停车场编号不能为空")
                                              @RequestParam("parkingId") Integer parkingId,
                                              @ApiParam(name = "车位x坐标", required = true)
                                              @NotNull(message = "车位x坐标不能为空")
                                              @RequestParam("x") Integer x,
                                              @ApiParam(name = "车位y坐标", required = true)
                                              @NotNull(message = "车位y坐标不能为空")
                                              @RequestParam("y") Integer y,
                                              @ApiParam(name = "车位状态", required = true)
                                              @NotNull(message = "车位状态不能为空")
                                              @RequestParam("status") Integer status) {
        return ResultData.success(parkingSpaceService.changeStatus(parkingId, x, y, status));
    }
}
