package com.insigma.cloud.auth.service;

import com.insigma.mvc.model.AccessToken;
import com.insigma.mvc.model.SUser;


/**
 * ��֤�ӿ�
 *
 * @author  admin
 */
public interface ApiAuthenticationService {

    //��ȡtoken
    AccessToken getToken(String username,String password) throws Exception;
    //��ȡtoken
    SUser getUser(String username,String password) throws Exception;

    SUser getUserByIdForGgfw(SUser suser);

}
