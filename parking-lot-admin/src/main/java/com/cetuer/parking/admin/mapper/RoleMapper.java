package com.cetuer.parking.admin.mapper;

import java.util.Set;

/**
 * 角色 数据层
 *
 * @author Cetuer
 * @date 2022/1/22 10:39
 */
public interface RoleMapper {
    /**
     * 根据用户ID查找其角色权限
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectRolePermsByUserId(Integer userId);
}
