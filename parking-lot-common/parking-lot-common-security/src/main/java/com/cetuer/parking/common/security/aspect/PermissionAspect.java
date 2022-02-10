package com.cetuer.parking.common.security.aspect;

import com.cetuer.parking.admin.api.model.LoginUser;
import com.cetuer.parking.common.core.constant.TokenConstants;
import com.cetuer.parking.common.core.service.RedisService;
import com.cetuer.parking.common.core.utils.ServletUtil;
import com.cetuer.parking.common.security.annotation.RequirePermission;
import com.cetuer.parking.common.security.enums.PermissionLogical;
import com.cetuer.parking.common.security.exception.NoPermissionException;
import com.cetuer.parking.common.security.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

/**
 * 权限校验切面
 *
 * @author Cetuer
 * @date 2022/2/7 11:16
 */
@Aspect
@Order(100)
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PermissionAspect {
    private final RedisService redisService;

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.cetuer.parking.common.security.annotation.RequirePermission)")
    public void pointcut() {
    }

    /**
     * 检查权限
     *
     * @param permission 权限注解对象
     * @throws NoPermissionException 如果验证未通过，则抛出异常
     */
    @Before("pointcut() && @annotation(permission)")
    public void hasPermission(RequirePermission permission) throws NoPermissionException {
        String[] permissions = permission.value();
        String userKey = ServletUtil.getHeader(TokenConstants.USER_KEY);
        LoginUser loginUser = SecurityUtil.getLoginUser(userKey);
        if (null == loginUser) {
            throw new NoPermissionException("用户过期");
        }
        Set<String> userPermissions = loginUser.getPermissions();
        boolean hasPerm;
        if (permission.logical() == PermissionLogical.AND) {
            hasPerm = Arrays.stream(permissions).allMatch(p -> userPermissions.contains("*:*:*") || userPermissions.contains(p));
        } else {
            hasPerm = Arrays.stream(permissions).anyMatch(p -> userPermissions.contains("*:*:*") || userPermissions.contains(p));
        }
        if (!hasPerm) {
            throw new NoPermissionException(permissions);
        }
    }
}
