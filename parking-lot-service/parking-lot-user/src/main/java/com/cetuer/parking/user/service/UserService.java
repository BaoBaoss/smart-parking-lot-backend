package com.cetuer.parking.user.service;

import com.cetuer.parking.user.api.domain.User;

/**
 * 用户操作业务层
 *
 * @author Cetuer
 * @date 2021/12/17 17:33
 */
public interface UserService {

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 用户
     */
    User findUserByUsername(String username);
}
