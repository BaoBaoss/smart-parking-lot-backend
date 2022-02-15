package com.cetuer.parking.admin.mapper;

import com.cetuer.parking.admin.domain.Role;

import java.util.List;

/**
 * 角色 数据层
 *
 * @author Cetuer
 * @date 2022/1/22 10:39
 */
public interface RoleMapper {

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
    List<Role> selectRolesByUserId(Integer userId);

    /**
     * 根据用户id判断是否是超级管理员角色
     * @param userId 用户id
     * @return true->是；false->否
     */
    Boolean isAdminRole(Integer userId);

    /**
     * 根据条件分页查找角色列表
     * @param role 条件
     * @return 角色列表
     */
    List<Role> selectRoleList(Role role);

    /**
     * 根据角色名查找角色
     * @param roleName 角色名
     * @return 角色
     */
    Role selectRoleByRoleName(String roleName);

    /**
     * 插入角色
     * @param role 角色
     */
    void insertRole(Role role);

    /**
     * 根据角色ID查找角色
     * @param roleId 角色ID
     * @return 角色
     */
    Role selectRolesByRoleId(Integer roleId);

    /**
     * 修改角色
     * @param role 角色
     */
    void updateRole(Role role);
}
