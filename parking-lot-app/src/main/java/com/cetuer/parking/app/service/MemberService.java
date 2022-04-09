package com.cetuer.parking.app.service;

import com.cetuer.parking.app.api.domain.Member;
import com.cetuer.parking.app.api.domain.vo.MemberLoginVo;

import java.util.List;

/**
* @author Administrator
* @description 针对表【member】的数据库操作Service
* @createDate 2022-04-02 14:19:31
*/
public interface MemberService {

    /**
     * 根据用户名查询会员信息
     *
     * @param username 用户名
     * @return 会员信息
     */
    Member selectByUsername(String username);

    /**
     * 插入会员
     * @param member 会员信息
     */
    void insert(MemberLoginVo member);

    /**
     * 根据条件分页查询会员列表
     *
     * @param member   查询条件
     * @return 会员列表
     */
    List<Member> selectListByPage(Member member);

    /**
     * 根据会员id获取会员信息
     *
     * @param id 会员id
     * @return 会员信息
     */
    Member selectByMemberId(Integer id);

    /**
     * 新增会员
     *
     * @param member 会员信息
     */
    void insertMember(Member member);

    /**
     * 根据ids列表批量删除会员
     *
     * @param ids           会员id列表
     * @return 删除个数
     */
    Integer deleteByIds(Integer[] ids);

    /**
     * 修改会员
     *
     * @param member 会员信息
     */
    void updateMember(Member member);

    /**
     * 重置用户密码
     *
     * @param member id和密码
     */
    void resetPwd(Member member);
}
