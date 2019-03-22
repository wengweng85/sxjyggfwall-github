package com.insigma.cloud.authorize.service;

import com.insigma.mvc.model.SPermission;
import com.insigma.mvc.model.SRole;

import java.util.List;


/**
 * 授权接口
 *
 * @author  admin
 */
public interface ApiAuthorizeService {

    //获取用户角色集合
    List<SRole> findRolesStr(String username) ;

    //获取用户权限集合
    List<SPermission> findPermissionStr(String username);

    //获取菜单树
    List<SPermission> findMenuTree(String username);

}
