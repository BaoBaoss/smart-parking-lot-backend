package com.cetuer.parking.admin.service;

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
}
