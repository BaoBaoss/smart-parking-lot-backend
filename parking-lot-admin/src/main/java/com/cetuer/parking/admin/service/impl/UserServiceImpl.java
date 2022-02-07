package com.cetuer.parking.admin.service.impl;

import com.cetuer.parking.admin.mapper.UserMapper;
import com.cetuer.parking.admin.service.UserService;
import com.cetuer.parking.admin.api.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户操作业务实现层
 *
 * @author Cetuer
 * @date 2021/12/17 17:34
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public User selectUserByUserId(Integer userId) {
        return userMapper.selectUserByUserId(userId);
    }

    @Override
    public List<User> selectUserListWithPage(User user) {
        return userMapper.selectUserList(user);
    }
}
