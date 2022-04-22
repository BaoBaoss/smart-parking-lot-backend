package com.cetuer.parking.app.service.impl;

import com.cetuer.parking.app.api.domain.Notice;
import com.cetuer.parking.app.mapper.NoticeMapper;
import com.cetuer.parking.app.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 公告操作业务层实现类
* 
* @author zhangqb
* @date 2022/4/13 10:28
*/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoticeServiceImpl implements NoticeService{
    private final NoticeMapper noticeMapper;

    @Override
    public List<Notice> selectListByPage(Integer parkingLotId) {
        return noticeMapper.selectAll(parkingLotId);
    }

    @Override
    public void insert(Notice notice) {
        noticeMapper.insert(notice);
    }

    @Override
    public Notice selectById(Integer noticeId) {
        return noticeMapper.selectById(noticeId);
    }

    @Override
    public void update(Notice notice) {
        noticeMapper.update(notice);
    }

    @Override
    public void delByIds(Integer[] ids) {
        noticeMapper.delByIds(ids);
    }

    @Override
    public List<Notice> selectListByParking(Integer parkingLotId) {
        return noticeMapper.selectShowByParking(parkingLotId);
    }
}
