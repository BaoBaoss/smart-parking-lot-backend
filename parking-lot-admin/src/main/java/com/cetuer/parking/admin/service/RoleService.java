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
     * @param  isAdmin 是否可以查询管理员角色
     * @param oneselfRole 自己拥有的角色
     * @return 角色列表
     */
    List<Role> selectRoleListByPage(Role role, boolean isAdmin, List<Integer> oneselfRoles);

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

    /**
     * 根据角色id列表删除角色
     * @param roleIds 角色id列表
     */
    void deleteByRoleIds(Integer[] roleIds);

    /**
     * 角色批量取消授权用户
     * @param userIds 用户id列表
     * @param roleId 角色id
     */
    void cancelAuthUser(Integer[] userIds, Integer roleId);

    /**
     * 批量授权用户
     * @param roleId 角色id
     * @param userIds 用户id列表
     */
    void authUsers(Integer roleId, Integer[] userIds);
}
