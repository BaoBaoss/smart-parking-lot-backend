package com.cetuer.parking.app.controller;

import com.cetuer.parking.app.api.domain.Notice;
import com.cetuer.parking.app.service.NoticeService;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.domain.TableInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公告表控制层
 *
 * @author zhangqb
 */
@Api(tags = "公告操作")
@Validated
@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoticeController {
    private final NoticeService noticeService;

    /**
     * 根据停车场分页查询公告列表
     *
     * @param parkingLotId 停车场编号
     * @return 公告列表
     */
    @ApiOperation("根据条件分页查询公告列表")
    @GetMapping("/listByPage")
    public ResultData<TableInfo<Notice>> listByPage(Integer parkingLotId) {
        List<Notice> noticeList = noticeService.selectListByPage(parkingLotId);
        return ResultData.success(TableInfo.getInstance(noticeList));
    }

    @ApiOperation("根据停车场id查询公告信息")
    @GetMapping("/listByParking")
    public ResultData<List<Notice>> listByParking(Integer parkingLotId) {
        return ResultData.success(noticeService.selectListByParking(parkingLotId));
    }

    @ApiOperation("新增公告信息")
    @PostMapping("/add")
    public ResultData<Void> add(@RequestBody Notice notice) {
        noticeService.insert(notice);
        return ResultData.success();
    }

    @ApiOperation("根据id获取公告信息")
    @GetMapping("/getNotice/{noticeId}")
    public ResultData<Notice> infoById(@PathVariable("noticeId") Integer noticeId) {
        return ResultData.success(noticeService.selectById(noticeId));
    }

    @ApiOperation("修改公告信息")
    @PutMapping("/update")
    public ResultData<Void> update(@RequestBody Notice notice) {
        noticeService.update(notice);
        return ResultData.success();
    }

    @ApiOperation("删除公告信息")
    @DeleteMapping("/del/{ids}")
    public ResultData<Void> del(@PathVariable("ids") Integer[] ids) {
        noticeService.delByIds(ids);
        return ResultData.success();
    }
}
