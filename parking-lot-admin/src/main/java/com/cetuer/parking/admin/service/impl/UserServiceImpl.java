package com.cetuer.parking.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.cetuer.parking.admin.api.domain.User;
import com.cetuer.parking.admin.domain.UserRole;
import com.cetuer.parking.admin.mapper.UserMapper;
import com.cetuer.parking.admin.mapper.UserRoleMapper;
import com.cetuer.parking.admin.service.UserService;
import com.cetuer.parking.common.security.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private final UserRoleMapper userRoleMapper;

    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public User selectUserByUserId(Integer userId) {
        return userMapper.selectUserByUserId(userId);
    }

    @Override
    public List<User> selectUserListByPage(User user, boolean isAdmin) {
        return isAdmin ? userMapper.selectUserList(user) : userMapper.selectUserListNoAdmin(user);
    }

    @Override
    @Transactional
    public Integer deleteByIds(Integer[] ids) {
        userRoleMapper.deleteByUserIds(ids);
        return userMapper.deleteByIds(ids);
    }

    @Override
    @Transactional
    public void insertUser(User user) {
        user.setPassword(SecurityUtil.encryptPassword(user.getPassword()));
        userMapper.insertUser(user);
        insertUserRole(user.getId(), user.getRoleIds());
    }

    private void insertUserRole(Integer userId, Set<Integer> roleIds) {
        if (CollectionUtil.isNotEmpty(roleIds)) {
            List<UserRole> userRoleList = new ArrayList<>();
            for (Integer roleId : roleIds) {
                userRoleList.add(new UserRole(userId, roleId));
            }
            if (userRoleList.size() > 0) {
                userRoleMapper.insertUserRole(userRoleList);
            }
        }
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        user.setPassword(SecurityUtil.encryptPassword(user.getPassword()));
        userRoleMapper.deleteByUserIds(new Integer[]{user.getId()});
        insertUserRole(user.getId(), user.getRoleIds());
        userMapper.updateUser(user);
    }

    @Override
    public void resetPwd(User user) {
        user.setPassword(SecurityUtil.encryptPassword(user.getPassword()));
        userMapper.updateUser(user);
    }

    @Override
    @Transactional
    public void updateUserRole(Integer userId, Set<Integer> roleIds) {
        userRoleMapper.deleteByUserIds(new Integer[]{userId});
        insertUserRole(userId, roleIds);
    }
}
