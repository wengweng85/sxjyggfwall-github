package com.insigma.cloud.auth.service.token;

import com.insigma.mvc.model.AccessToken;
import com.insigma.mvc.model.SPermission;
import com.insigma.mvc.model.SRole;
import com.insigma.mvc.model.SUser;

import java.util.List;


/**
 * 认证接口
 *
 * @author xxx
 */
public interface ApiTokenService {


    //获取用户角色集合
    List<SRole> findRolesStr(String username) ;

    //获取用户权限集合
    List<SPermission> findPermissionStr(String username);

    //获取token
    AccessToken getToken( SUser suser) throws Exception;

    //刷新token
    /*AccessToken refreshToken(String token) throws Exception;*/

    
    
}
