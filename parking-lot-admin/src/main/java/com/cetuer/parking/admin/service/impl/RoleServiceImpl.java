package com.cetuer.parking.admin.service.impl;

import com.cetuer.parking.admin.domain.Role;
import com.cetuer.parking.admin.mapper.RoleMapper;
import com.cetuer.parking.admin.service.RoleService;
import com.cetuer.parking.admin.util.AdminUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public Set<String> selectRolePermsByUserId(Integer userId) {
        Set<String> roles = new HashSet<>();
        //管理员拥有所有权限
        if (AdminUtil.isAdminUser(userId)) {
            roles.add("admin");
        } else {
            roles.addAll(roleMapper.selectRolePermsByUserId(userId));
        }
        return roles;
    }

    @Override
    public List<Role> selectRoleAll() {
        return roleMapper.selectRoleAll();
    }

    @Override
    public List<Integer> selectRoleIdsByUserId(Integer userId) {
        return roleMapper.selectRoleIdsByUserId(userId);
    }
}
