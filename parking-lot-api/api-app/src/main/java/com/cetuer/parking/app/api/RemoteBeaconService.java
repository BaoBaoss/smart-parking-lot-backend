package com.cetuer.parking.app.api;


import com.cetuer.parking.app.api.domain.vo.BeaconDevice;
import com.cetuer.parking.app.api.factory.RemoteBeaconFallbackFactory;
import com.cetuer.parking.common.core.constant.ServiceNameConstants;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.domain.TableInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

/**
 * 远程调用信标服务
 *
 * @author Cetuer
 * @date 2021/12/17 10:31
 */
@FeignClient(contextId = "remoteBeaconService", value = ServiceNameConstants.APP_SERVICE, fallbackFactory = RemoteBeaconFallbackFactory.class)
public interface RemoteBeaconService {

    @ApiOperation("分页查询信标信息")
    @GetMapping("/beacon/listByPage")
    ResultData<TableInfo<BeaconDevice>> listByPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @SpringQueryMap BeaconDevice beaconDevice);

    @ApiOperation("删除停车场所有信标信息")
    @DeleteMapping("/beacon/delAll/{parkingId}")
    ResultData<Void> delAll(@PathVariable("parkingId") Integer parkingId);

    @ApiOperation("新增信标信息")
    @PostMapping("/beacon/add")
    ResultData<Void> add(@RequestBody BeaconDevice beaconDevice);

    @ApiOperation("根据id获取信标信息")
    @GetMapping("/beacon/getBeacon/{beaconId}")
    ResultData<BeaconDevice> infoById(@PathVariable("beaconId") Integer beaconId);

    @ApiOperation("修改信标信息")
    @PutMapping("/beacon/update")
    ResultData<Void> update(@RequestBody BeaconDevice beaconDevice);

    @ApiOperation("删除信标")
    @DeleteMapping("/beacon/del/{ids}")
    ResultData<Void> del(@PathVariable("ids") Integer[] ids);
}
