package com.cetuer.parking.common.aspect;

import cn.hutool.core.util.ObjectUtil;
import com.cetuer.parking.common.constant.TableConstants;
import com.cetuer.parking.common.domain.ResultData;
import com.cetuer.parking.common.domain.TableInfo;
import com.cetuer.parking.common.utils.ServletUtil;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 分页切面
 *
 * @author Cetuer
 * @date 2022/2/4 11:46
 */
@Component
@Aspect
@Slf4j
public class PageAspect {

    /**
     * 以WithPage结尾的Controller方法都是需要分页的方法，此后置增强用于添加分页信息
     * @param joinPoint 连接点
     * @param val 方法返回值
     */
    @AfterReturning(value = "execution(public * com.cetuer.parking.*.controller.*.*WithPage(..))", returning = "val")
    public void controllerAop(JoinPoint joinPoint, ResultData<? extends TableInfo<?>> val) {

    }

    /**
     * 以WithPage结尾的ServiceImpl方法都是需要分页的方法，此前置增强用于分页
     */
    @Before(value = "execution(public * com.cetuer.parking.*.service.impl.*.*WithPage(..))")
    public void serviceImplAop() {
        Integer pageNum = ServletUtil.getParameterToInt(TableConstants.PAGE_NUM);
        Integer pageSize = ServletUtil.getParameterToInt(TableConstants.PAGE_SIZE);
        if(ObjectUtil.isNotNull(pageNum) && ObjectUtil.isNotNull(pageSize)) {
            PageHelper.startPage(pageNum, pageSize);
        }
    }
}
