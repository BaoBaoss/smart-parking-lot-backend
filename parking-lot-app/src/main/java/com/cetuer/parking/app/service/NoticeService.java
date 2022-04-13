package com.cetuer.parking.app.service;

import com.cetuer.parking.app.api.domain.Notice;

import java.util.List;

/**
 * 公告操作业务层接口
 *
 * @author zhangqb
 * @date 2022/4/13 10:28
 */
public interface NoticeService {

    /**
     * 分页查询公告列表
     * @param parkingLotId 停车场编号
     * @return 公告列表
     */
    List<Notice> selectListByPage(Integer parkingLotId);

    /**
     * 插入公告
     * @param notice 公告
     */
    void insert(Notice notice);

    /**
     * 根据id查找公告
     * @param noticeId 公告id
     * @return 公告
     */
    Notice selectById(Integer noticeId);

    /**
     * 修改公告
     * @param notice 公告
     */
    void update(Notice notice);

    /**
     * 批量删除公告
     * @param ids 公告id列表
     */
    void delByIds(Integer[] ids);
}
