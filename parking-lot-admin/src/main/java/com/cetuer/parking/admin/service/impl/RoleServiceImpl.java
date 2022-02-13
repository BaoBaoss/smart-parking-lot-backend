package com.cetuer.parking.admin.service.impl;

import com.cetuer.parking.admin.domain.Role;
import com.cetuer.parking.admin.mapper.RoleMapper;
import com.cetuer.parking.admin.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色操作 业务实现层
 *
 * @author Cetuer
 * @date 2022/1/21 22:11
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;

    @Override
    public List<Role> selectRoleAll() {
        return roleMapper.selectRoleAll();
    }

    @Override
    public List<Integer> selectRoleIdsByUserId(Integer userId) {
        return roleMapper.selectRolesByUserId(userId).stream().map(Role::getId).collect(Collectors.toList());
    }

    @Override
    public String selectRoleGroupByUserId(Integer userId) {
        return roleMapper.selectRolesByUserId(userId).stream().map(Role::getName).collect(Collectors.joining(","));
    }
}
