package com.cetuer.parking.admin.mapper;

import com.cetuer.parking.admin.domain.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 用户角色数据层
* 
* @author Cetuer
* @date 2022/2/8 10:07
*/
public interface UserRoleMapper {

    /**
     * 根据用户ids批量删除相关数据
     * @param ids 用户id列表
     * @return 删除个数
     */
    Integer deleteByUserIds(Integer[] ids);

    /**
     * 插入用户与角色关联数据
     * @param userRoleList 数据列表
     */
    void insertUserRole(List<UserRole> userRoleList);


    /**
     * 根据角色id查询使用此角色的用户数
     * @param roleId 角色ID
     * @return 用户数
     */
    Integer countUserByRoleId(Integer roleId);

    /**
     * 根据用户id和角色id删除数据
     * @param userIds 用户id列表
     * @param roleId 角色id
     */
    void deleteByUserIdsAndRoleId(@Param("userIds") Integer[] userIds, @Param("roleId") Integer roleId);
}