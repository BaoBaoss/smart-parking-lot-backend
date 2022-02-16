package com.cetuer.parking.admin.service;

import com.cetuer.parking.admin.api.domain.User;

import java.util.List;
import java.util.Set;

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
    User selectUserByUsername(String username);

    /**
     * 根据用户ID查找用户
     * @param userId 用户ID
     * @return 用户
     */
    User selectUserByUserId(Integer userId);

    /**
     * 根据条件分页查找用户列表
     * @param user 条件
     * @param isAdmin 是否可以查询管理员用户
     * @return 用户列表
     */
    List<User> selectUserListByPage(User user, boolean isAdmin);

    /**
     * 根据用户ids批量删除用户
     * @param ids id列表
     * @return 删除个数
     */
    Integer deleteByIds(Integer[] ids);

    /**
     * 新增用户
     * @param user 用户
     */
    void insertUser(User user);

    /**
     * 修改用户
     * @param user 用户信息
     */
    void updateUser(User user);

    /**
     * 重置密码
     * @param user 用户id和密码
     */
    void resetPwd(User user);

    /**
     * 更新用户与角色关联
     * @param userId 用户
     * @param roleIds 角色
     */
    void updateUserRole(Integer userId, Set<Integer> roleIds);

    /**
     * 更新用户头像
     * @param id 用户id
     * @param avatar 头像访问地址
     */
    void updateAvatar(Integer id, String avatar);

    /**
     * 根据角色id分页查询分配此角色用户列表
     * @param roleId 角色id
     * @param username 搜索条件：用户名
     * @param phone 搜索条件：手机号码
     * @return 分配此角色用户列表
     */
    List<User> selectAllocatedListByPage(Integer roleId, String username, String phone);

    /**
     * 根据角色id分页查询未分配此角色用户列表
     * @param roleId 角色id
     * @param username 搜索条件：用户名
     * @param phone 搜索条件：手机号码
     * @return 未分配此角色用户列表
     */
    List<User> selectUnallocatedListByPage(Integer roleId, String username, String phone);
}
