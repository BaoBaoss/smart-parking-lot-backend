package com.cetuer.parking.app.service.impl;

import com.cetuer.parking.app.api.domain.Member;
import com.cetuer.parking.app.api.domain.vo.MemberLoginVo;
import com.cetuer.parking.app.mapper.MemberMapper;
import com.cetuer.parking.app.service.MemberService;
import com.cetuer.parking.common.security.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Administrator
* @description 针对表【member】的数据库操作Service实现
* @createDate 2022-04-02 14:19:31
*/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;

    @Override
    public Member selectByUsername(String username) {
        return memberMapper.selectByUsername(username);
    }

    @Override
    public void insert(MemberLoginVo member) {
        member.setPassword(SecurityUtil.encryptPassword(member.getPassword()));
        memberMapper.insert(member);
    }

    @Override
    public List<Member> selectListByPage(Member member) {
        return memberMapper.selectListByPage(member);
    }

    @Override
    public Member selectByMemberId(Integer id) {
        return memberMapper.selectByMemberId(id);
    }

    @Override
    public void insertMember(Member member) {
        member.setPassword(SecurityUtil.encryptPassword(member.getPassword()));
        memberMapper.insertMember(member);
    }

    @Override
    public Integer deleteByIds(Integer[] ids) {
        return memberMapper.deleteByIds(ids);
    }

    @Override
    public void updateMember(Member member) {
        memberMapper.updateMember(member);
    }

    @Override
    public void resetPwd(Member member) {
        member.setPassword(SecurityUtil.encryptPassword(member.getPassword()));
        memberMapper.updateMember(member);
    }
}
