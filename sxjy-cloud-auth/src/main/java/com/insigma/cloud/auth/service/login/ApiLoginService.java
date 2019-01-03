package com.insigma.cloud.auth.service.login;

import com.insigma.mvc.model.LoginInf;
import com.insigma.mvc.model.SPermission;
import com.insigma.mvc.model.SRole;
import com.insigma.mvc.model.SUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 登录service接口
 *
 * @author xxx
 */
public interface ApiLoginService {


	SUser getUserByUsername(String username) ;
	

    /**
     * 通过用户id获取用户角色集合
     *
     * @param loginname
     * @return 角色集合
     * @throws Exception
     */
    List<SRole> findRolesStr(String loginname) ;

    /**
     * 通过用户id获取用户权限集合
     *
     * @param loginname
     * @return 权限集合
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
     * 获取用户信息并保存登录日志
     *
     * @param suser
     * @return
     */
    SUser login(HttpServletRequest request, SUser suser) throws Exception;


    SUser getUserByIdForGgfw(SUser suser);
    
    
}
