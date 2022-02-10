package com.cetuer.parking.admin.util;

import cn.hutool.extra.spring.SpringUtil;
import com.cetuer.parking.admin.mapper.RoleMapper;

/**
 * 管理员相关工具类
 *
 * @author Cetuer
 * @date 2022/2/10 16:19
 */
public class AdminUtil {
    private static RoleMapper ROLE_MAPPER;

    /**
     * 是否是超级管理员角色
     * @param userId 用户Id
     * @return true->是；false->否
     */
    public static boolean isAdminRole(Integer userId) {
        if(null == userId) {
            return false;
        }
        return getRoleMapper().isAdminRole(userId);
    }

    /**
     * 是否是超级管理员用户
     * @param userId 用户Id
     * @return true->是；false->否
     */
    public static boolean isAdminUser(Integer userId) {
        return getAdminUserId().equals(userId);
    }

    /**
     * 获得超级管理员id
     *
     * @return 超级管理员id
     */
    public static Integer getAdminUserId() {
        return 1;
    }

    /**
     * 是否有所有权限
     * @param userId 用户id
     * @return true->有；false->没有
     */
    public static boolean hasAllPermission(Integer userId) {
        return isAdminUser(userId) || isAdminRole(userId);
    }

    private static RoleMapper getRoleMapper() {
        if(ROLE_MAPPER == null) {
            ROLE_MAPPER = SpringUtil.getBean(RoleMapper.class);
        }
        return ROLE_MAPPER;
    }
}
