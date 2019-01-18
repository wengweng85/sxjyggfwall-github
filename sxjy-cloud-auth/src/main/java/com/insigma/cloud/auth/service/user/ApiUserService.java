package com.insigma.cloud.auth.service.user;

import com.insigma.mvc.model.SUser;


/**
 * 用户登录注册相关服务
 *
 * @author  admin
 */
public interface ApiUserService {

    SUser getUserByIdForGgfw(SUser suser);

}
