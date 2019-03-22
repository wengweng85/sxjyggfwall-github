package com.insigma.cloud.authorize.service;

import com.insigma.mvc.model.SPermission;
import com.insigma.mvc.model.SRole;

import java.util.List;


/**
 * ��Ȩ�ӿ�
 *
 * @author  admin
 */
public interface ApiAuthorizeService {

    //��ȡ�û���ɫ����
    List<SRole> findRolesStr(String username) ;

    //��ȡ�û�Ȩ�޼���
    List<SPermission> findPermissionStr(String username);

    //��ȡ�˵���
    List<SPermission> findMenuTree(String username);

}
