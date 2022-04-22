package com.cetuer.parking.app.mapper;

import com.cetuer.parking.app.api.domain.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 公告操作数据层
 *
 * @author zhangqb
 * @date 2022/4/13 10:28
 */
public interface NoticeMapper {

    /**
     * 根据停车场编号查找所有公告，如果为空则查找所有
     *
     * @param parkingLotId 停车场编号
     * @return 公告列表
     */
    List<Notice> selectAll(@Param("parkingLotId") Integer parkingLotId);

    /**
     * 插入公告
     *
     * @param notice 公告
     */
    void insert(Notice notice);

    /**
     * 根据id查找公告
     *
     * @param noticeId 公告id
     * @return 公告
     */
    Notice selectById(Integer noticeId);

    /**
     * 修改公告
     *
     * @param notice 公告
     */
    void update(Notice notice);

    /**
     * 批量删除公告
     *
     * @param ids 公告id列表
     */
    void delByIds(Integer[] ids);

    /**
     * 根据停车场ID查询展示的公告
     * @param parkingLotId 停车场ID
     * @return 公告列表
     */
    List<Notice> selectShowByParking(@Param("parkingLotId") Integer parkingLotId);
}