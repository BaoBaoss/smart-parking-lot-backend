package com.cetuer.parking.admin.service;

import com.cetuer.parking.admin.domain.Role;

import java.util.List;
import java.util.Set;

/**
 * 角色操作业务层
 *
 * @author Cetuer
 * @date 2022/1/21 22:10
 */
public interface RoleService {
    /**
     * 根据用户ID查找其角色权限
     * @param userId 用户ID
     * @return 角色权限列表
     */
    Set<String> selectRolePermsByUserId(Integer userId);

    /**
     * 查找所有角色信息
     * @return 角色列表
     */
    List<Role> selectRoleAll();

    /**
     * 根据用户id查找其角色id列表
     * @param userId 用户id
     * @return 角色id列表
     */
    List<Integer> selectRoleIdsByUserId(Integer userId);
}
