package com.insigma.cloud.auth.service.token;

import com.insigma.mvc.model.AccessToken;
import com.insigma.mvc.model.SPermission;
import com.insigma.mvc.model.SRole;
import com.insigma.mvc.model.SUser;

import java.util.List;


/**
 * ��֤�ӿ�
 *
 * @author xxx
 */
public interface ApiTokenService {


    //��ȡ�û���ɫ����
    List<SRole> findRolesStr(String username) ;

    //��ȡ�û�Ȩ�޼���
    List<SPermission> findPermissionStr(String username);

    //��ȡtoken
    AccessToken getToken( SUser suser) throws Exception;

    //ˢ��token
    /*AccessToken refreshToken(String token) throws Exception;*/

    
    
}
