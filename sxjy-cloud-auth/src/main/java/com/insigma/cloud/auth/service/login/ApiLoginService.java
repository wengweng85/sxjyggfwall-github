package com.insigma.cloud.auth.service.login;

import com.insigma.mvc.model.LoginInf;
import com.insigma.mvc.model.SPermission;
import com.insigma.mvc.model.SRole;
import com.insigma.mvc.model.SUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * ��¼service�ӿ�
 *
 * @author xxx
 */
public interface ApiLoginService {


	SUser getUserByUsername(String username) ;
	

    /**
     * ͨ���û�id��ȡ�û���ɫ����
     *
     * @param loginname
     * @return ��ɫ����
     * @throws Exception
     */
    List<SRole> findRolesStr(String loginname) ;

    /**
     * ͨ���û�id��ȡ�û�Ȩ�޼���
     *
     * @param loginname
     * @return Ȩ�޼���
     * @throws Exception
     */
    List<SPermission> findPermissionStr(String loginname);

    /**
     * @param inf
     */
    void saveLoginHashInfo(LoginInf inf);

    /**
     * @param loginhash
     * @return
     */
    LoginInf findLoginInfoByhashcode(String loginhash);

    /**
     * ��ȡ�û���Ϣ�������¼��־
     *
     * @param suser
     * @return
     */
    SUser login(HttpServletRequest request, SUser suser) throws Exception;


    SUser getUserByIdForGgfw(SUser suser);
    
    
}
