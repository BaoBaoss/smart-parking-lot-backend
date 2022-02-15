package com.cetuer.parking.admin.service;

import com.cetuer.parking.admin.domain.Role;

import java.util.List;

/**
 * 角色操作业务层
 *
 * @author Cetuer
 * @date 2022/1/21 22:10
 */
public interface RoleService {

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

    /**
     * 查询用户所属角色组
     *
     * @param userId 用户唯一标识
     * @return 角色组
     */
    String selectRoleGroupByUserId(Integer userId);

    /**
     * 根据条件分页查找角色列表
     * @param role 条件
     * @return 角色列表
     */
    List<Role> selectRoleListByPage(Role role);

    /**
     * 根据角色名查找角色
     * @param roleName 角色名
     * @return 角色
     */
    Role selectRoleByRoleName(String roleName);

    /**
     * 新增角色
     * @param role 角色
     */
    void insertRole(Role role);

    /**
     * 根据角色ID查找角色
     * @param roleId 角色ID
     * @return 角色
     */
    Role selectRoleByRoleId(Integer roleId);

    /**
     * 修改角色
     * @param role 角色
     */
    void updateRole(Role role);
}
