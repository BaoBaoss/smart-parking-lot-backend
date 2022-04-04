package com.cetuer.parking.app.controller;

import com.cetuer.parking.app.api.domain.ParkingSpace;
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

    @ApiOperation("删除停车场所有停车位信息")
    @DeleteMapping("/delAll/{parkingId}")
    public ResultData<Void> delAll(@PathVariable("parkingId") Integer parkingId) {
        parkingSpaceService.delAllByParkingId(parkingId);
        return ResultData.success();
    }

    @ApiOperation("分页查询车位信息")
    @GetMapping("/listByPage")
    public ResultData<TableInfo<ParkingSpace>> listByPage(Integer parkingId) {
        return ResultData.success(TableInfo.getInstance(parkingSpaceService.selectListByPage(parkingId)));
    }

    @ApiOperation("新增车位信息")
    @PostMapping("/add")
    public ResultData<Void> add(@RequestBody ParkingSpace parkingSpace) {
        if(parkingSpaceService.hasSpace(parkingSpace.getParkingLotId(), parkingSpace.getX(), parkingSpace.getY())) {
            throw new ServiceException(ResultCode.ADD_SPACE_HAS_SPACE_ERROR);
        }
        parkingSpaceService.insert(parkingSpace);
        return ResultData.success();
    }

    @ApiOperation("根据id获取车位信息")
    @GetMapping("/getSpace/{spaceId}")
    public ResultData<ParkingSpace> infoById(@PathVariable("spaceId") Integer spaceId) {
        return ResultData.success(parkingSpaceService.selectById(spaceId));
    }

    @ApiOperation("修改车位信息")
    @PutMapping("/update")
    public ResultData<Void> update(@RequestBody ParkingSpace parkingSpace) {
        if(parkingSpaceService.hasSpace(parkingSpace.getParkingLotId(), parkingSpace.getX(), parkingSpace.getY())) {
            throw new ServiceException(ResultCode.ADD_SPACE_HAS_SPACE_ERROR);
        }
        parkingSpaceService.update(parkingSpace);
        return ResultData.success();
    }

    @ApiOperation("删除车位")
    @DeleteMapping("/del/{ids}")
    public ResultData<Void> del(@PathVariable("ids") Integer[] ids) {
        parkingSpaceService.del(ids);
        return ResultData.success();
    }
}
