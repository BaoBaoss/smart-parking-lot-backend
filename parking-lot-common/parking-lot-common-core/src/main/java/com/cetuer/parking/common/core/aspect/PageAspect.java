package com.cetuer.parking.common.core.aspect;

import cn.hutool.core.util.ObjectUtil;
import com.cetuer.parking.common.core.constant.TableConstants;
import com.cetuer.parking.common.core.utils.ServletUtil;
import com.github.pagehelper.PageHelper;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 分页切面
 *
 * @author Cetuer
 * @date 2022/2/4 11:46
 */

@Aspect
@Order(200)
@Component
public class PageAspect {

    /**
     * 以WithPage结尾的ServiceImpl方法都是需要分页的方法，此前置增强用于分页
     */
    @Before(value = "execution(public * com.cetuer.parking.*.service.impl.*.*ByPage(..))")
    public void serviceImplAop() {
        Integer pageNum = ServletUtil.getParameterToInt(TableConstants.PAGE_NUM);
        Integer pageSize = ServletUtil.getParameterToInt(TableConstants.PAGE_SIZE);
        if(ObjectUtil.isNotNull(pageNum) && ObjectUtil.isNotNull(pageSize)) {
            PageHelper.startPage(pageNum, pageSize);
        }
    }
}
