package com.cetuer.parking.auth.service;

import com.cetuer.parking.common.constant.UserConstant;
import com.cetuer.parking.common.domain.ResultData;
import com.cetuer.parking.common.enums.ResultCode;
import com.cetuer.parking.common.exception.ServiceException;
import com.cetuer.parking.common.utils.SecurityUtil;
import com.cetuer.parking.admin.api.RemoteUserService;
import com.cetuer.parking.admin.api.domain.User;
import com.cetuer.parking.admin.api.model.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录业务层
 *
 * @author Cetuer
 * @date 2021/12/17 10:07
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginService {

    private final RemoteUserService remoteUserService;

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    public LoginUser login(String username, String password) {
        ResultData<LoginUser> resultData = remoteUserService.getUserInfo(username);
        if(ResultCode.ACCOUNT_NOT_EXIST.getCode() == resultData.getStatus() || null == resultData.getData()) {
            throw new ServiceException(ResultCode.ACCOUNT_NOT_EXIST);
        }
        LoginUser userInfo = resultData.getData();
        User user = userInfo.getUser();
        if(UserConstant.DISABLE == user.getStatus()) {
            throw new ServiceException(ResultCode.ACCOUNT_DISABLE);
        }
        if(!SecurityUtil.matchesPassword(password, user.getPassword())) {
            throw new ServiceException(ResultCode.ACCOUNT_PASSWORD_ERROR);
        }
        return userInfo;
    }
}
