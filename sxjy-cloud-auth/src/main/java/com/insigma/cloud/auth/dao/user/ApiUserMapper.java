package com.insigma.cloud.auth.dao.user;

import com.insigma.mvc.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 登录service接口
 *
 * @author  admin
 */
@Mapper
public interface ApiUserMapper {

    /**
     * 通过用户名获取会员表信息
     *
     * @param username
     * @param password
     * @return 用户表
     * @throws Exception
     */
    SUser getUserByUsername(@Param("username") String username, @Param("password") String password);


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



    /**
     * 记录登录信息
     *
     * @param user
     */
    void updateLogintimes(SUser user);

    /**
     * getUserByUserNameForGgfw
     * @param user
     * @return
     */
    SUser getUserByUserNameForGgfw(SUser user);

    /**
     * addSUserForGgfw
     * @param suser
     * @return
     */
    int  addSUserForGgfw(SUser suser);

    /**
     * getAc01ByAac002ForGgfw
     * @param aac002
     * @return
     */
    Ac01 getAc01ByAac002ForGgfw(String aac002);

    /**
     * getAb01ByAab998ForGgfw
     * @param aac002
     * @return
     */
    Ab01 getAb01ByAab998ForGgfw(String aac002);
}
