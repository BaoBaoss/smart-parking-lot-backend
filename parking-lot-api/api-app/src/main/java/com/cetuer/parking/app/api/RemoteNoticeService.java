package com.cetuer.parking.app.api;


import com.cetuer.parking.app.api.domain.Notice;
import com.cetuer.parking.app.api.factory.RemoteNoticeFallbackFactory;
import com.cetuer.parking.common.core.constant.ServiceNameConstants;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.domain.TableInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 远程调用公告服务
 *
 * @author Cetuer
 * @date 2021/12/17 10:31
 */
@FeignClient(contextId = "remoteNoticeService", value = ServiceNameConstants.APP_SERVICE, fallbackFactory = RemoteNoticeFallbackFactory.class)
public interface RemoteNoticeService {
    @ApiOperation("分页查询公告信息")
    @GetMapping("/notice/listByPage")
    ResultData<TableInfo<Notice>> listByPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "parkingLotId", required = false) Integer parkingLotId);

    @ApiOperation("新增公告信息")
    @PostMapping("/notice/add")
    ResultData<Void> add(@RequestBody Notice notice);

    @ApiOperation("根据id获取公告信息")
    @GetMapping("/notice/getNotice/{noticeId}")
    ResultData<Notice> infoById(@PathVariable("noticeId") Integer noticeId);

    @ApiOperation("修改公告信息")
    @PutMapping("/notice/update")
    ResultData<Void> update(@RequestBody Notice notice);

    @ApiOperation("删除公告信息")
    @DeleteMapping("/notice/del/{ids}")
    ResultData<Void> del(@PathVariable("ids") Integer[] ids);
}
