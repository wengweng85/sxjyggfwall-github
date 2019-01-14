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

    //��ȡ�˵���
    List<SPermission> findMenuTree(String username);

    //��ȡtoken
    AccessToken getToken(String username,String password) throws Exception;


    
    
}
