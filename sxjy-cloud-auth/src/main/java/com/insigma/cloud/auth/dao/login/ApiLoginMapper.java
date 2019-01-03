package com.insigma.cloud.auth.dao.login;

import com.insigma.mvc.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 登录service接口
 *
 * @author xxx
 */
@Mapper
public interface ApiLoginMapper {

    /**
     * 通过用户名获取会员表信息
     *
     * @param username
     * @return 用户表
     * @throws Exception
     */
    SUser getUserByUsername(@Param("username") String username);


    /**
     * 通过用户id获取用户角色集合
     *
     * @return 角色集合
     * @throws Exception
     */
    List<SRole> findRolesStr(String loginname);


    /**
     * 通过用户id获取用户权限集合
     *
     * @return 权限集合
     * @throws Exception
     */
    List<SPermission> findPermissionStr(String loginname);

    /**
     * 保存hashinfo
     *
     * @param inf
     */
    void saveLoginHashInfo(LoginInf inf);


    LoginInf findLoginInfoByhashcode(String loginhash);

    /**
     * 记录登录信息
     *
     * @param user
     */
    void updateLogintimes(SUser user);


    
    SUser getUserByUserNameForGgfw(SUser user);
    
    int  addSUserForGgfw(SUser suser);
    
    Ac01 getAc01ByAac002ForGgfw(String aac002);
    
    Ab01 getAb01ByAab998ForGgfw(String aac002);
}
