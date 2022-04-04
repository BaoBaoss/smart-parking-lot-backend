package com.cetuer.parking.admin.controller;

import com.cetuer.parking.app.api.RemoteParkingSpaceService;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.security.annotation.RequirePermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 停车位操作
 *
 * @author zhangqb
 * @date 2022/4/4 10:55
 */
@Api(tags = "停车位操作")
@RestController
@RequestMapping("/parkingSpace")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ParkingSpaceController {
    private final RemoteParkingSpaceService remoteParkingSpaceService;

    @ApiOperation("删除停车场所有停车位信息")
    @DeleteMapping("/delAll/{parkingId}")
    @RequirePermission("app:space:remove")
    public ResultData<Void> delAll(@PathVariable("parkingId") Integer parkingId) {
        return remoteParkingSpaceService.delAll(parkingId);
    }
}
