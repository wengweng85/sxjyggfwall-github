package com.insigma.cloud.authorize.dao;

import com.insigma.mvc.model.SPermission;
import com.insigma.mvc.model.SRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 授权服务
 *
 * @author  admin
 */
@Mapper
public interface ApiAuthorizeMapper {


    /**
     * 通过用户id获取用户角色集合
     *
     * @param username
     * @return 角色集合
     * @throws Exception
     */
    List<SRole> findRolesStr(String username);


    /**
     * 通过用户id获取用户权限集合
     *
     * @param  username
     * @return 权限集合
     * @throws Exception
     */
    List<SPermission> findPermissionStr(String username);

}
