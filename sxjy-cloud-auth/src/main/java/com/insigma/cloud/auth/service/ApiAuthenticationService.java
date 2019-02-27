package com.insigma.cloud.auth.service;

import com.insigma.mvc.model.AccessToken;
import com.insigma.mvc.model.SUser;


/**
 * 认证接口
 *
 * @author  admin
 */
public interface ApiAuthenticationService {

    //获取token
    AccessToken getToken(String username,String password) throws Exception;
    //获取token
    SUser getUser(String username,String password) throws Exception;

    SUser getUserByIdForGgfw(SUser suser);

}
