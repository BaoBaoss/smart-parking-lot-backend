package com.cetuer.parking.admin.mapper;

import com.cetuer.parking.admin.api.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户操作数据层
 *
 * @author Cetuer
 * @date 2021/12/17 17:36
 */
public interface UserMapper {

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
     * 根据条件查找用户列表
     * @param user 查找条件
     * @return 用户列表
     */
    List<User> selectUserList(User user);

    /**
     * 根据条件查找用户列表（无管理员用户）
     * @param user 查找条件
     * @return 用户列表
     */
    List<User> selectUserListNoAdmin(User user);

    /**
     * 根据用户ids批量删除用户
     * @param ids id列表
     * @return 删除个数
     */
    Integer deleteByIds(Integer[] ids);

    /**
     * 新增用户
     * @param user 用户信息
     */
    void insertUser(User user);

    /**
     * 修改用户
     * @param user 用户信息
     */
    void updateUser(User user);

    /**
     * 更新头像地址
     * @param id 用户id
     * @param avatar 头像地址
     */
    void updateAvatar(@Param("id") Integer id, @Param("avatar") String avatar);
}
