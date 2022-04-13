package com.cetuer.parking.admin.controller;

import com.cetuer.parking.app.api.RemoteNoticeService;
import com.cetuer.parking.app.api.domain.Notice;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.domain.TableInfo;
import com.cetuer.parking.common.security.annotation.RequirePermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 公告操作
 *
 * @author zhangqb
 * @date 2022/4/13 10:55
 */
@Api(tags = "公告操作")
@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoticeController {
    private final RemoteNoticeService remoteNoticeService;

    @ApiOperation("分页公告信息")
    @GetMapping("/listByPage")
    @RequirePermission("app:notice:list")
    public ResultData<TableInfo<Notice>> listByPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, Integer parkingLotId) {
        return remoteNoticeService.listByPage(pageNum, pageSize, parkingLotId);
    }

    @ApiOperation("新增公告信息")
    @PostMapping("/add")
    @RequirePermission("app:notice:add")
    public ResultData<Void> add(@RequestBody Notice notice) {
        return remoteNoticeService.add(notice);
    }

    @ApiOperation("根据id获取公告信息")
    @GetMapping("/getNotice/{noticeId}")
    public ResultData<Notice> infoById(@PathVariable("noticeId") Integer noticeId) {
        return remoteNoticeService.infoById(noticeId);
    }

    @ApiOperation("修改公告信息")
    @PutMapping("/update")
    @RequirePermission("app:notice:edit")
    public ResultData<Void> update(@RequestBody Notice notice) {
        return remoteNoticeService.update(notice);
    }

    @ApiOperation("删除公告信息")
    @DeleteMapping("/del/{ids}")
    @RequirePermission("app:notice:remove")
    public ResultData<Void> del(@PathVariable("ids") Integer[] ids) {
        return remoteNoticeService.del(ids);
    }
}
