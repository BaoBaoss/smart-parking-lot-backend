package com.cetuer.parking.user.service.impl;

import com.cetuer.parking.user.api.domain.User;
import com.cetuer.parking.user.mapper.UserMapper;
import com.cetuer.parking.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 用户
     */
    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }
}
