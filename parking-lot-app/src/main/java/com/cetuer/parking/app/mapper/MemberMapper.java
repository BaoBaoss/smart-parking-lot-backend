package com.cetuer.parking.app.mapper;

import com.cetuer.parking.app.api.domain.Member;
import com.cetuer.parking.app.api.domain.vo.MemberLoginVo;

/**
* @author Administrator
* @description 针对表【member】的数据库操作Mapper
* @createDate 2022-04-02 14:19:30
* @Entity com.cetuer.parking.app.api.domain.Member
*/
public interface MemberMapper {

    /**
     * 根据用户名查询会员信息
     *
     * @param username 用户名
     * @return 会员信息
     */
    Member selectByUsername(String username);

    /**
     * 插入会员
     * @param member 会员
     * @return 无
     */
    void insert(MemberLoginVo member);
}
