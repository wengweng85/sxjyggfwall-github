package com.insigma.cloud.base.dao.login;

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
     * 通过userid获取会员表信息
     *
     * @param userid
     * @return 用户表
     * @throws Exception
     */
    SUser getUserByUserid(@Param("userid") String userid);

    /**
     * 通过用户名和密码获取会员表信息
     *
     * @param username
     * @param password
     * @return
     */
    SUser getUserByUsernamePass(@Param("username") String username, @Param("password") String password);

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

    /**
     * 根据openId获取帐号信息
     *
     * @param openId
     * @return
     */
    SUser getSUserByOpenId(String openId);

    /**
     * 保存openId
     *
     * @param user
     */
    void saveOpenId(SUser user);

    /**
     * 根据baseinfoid获取用户信息
     *
     * @param baseinfoid
     * @return
     */
    SUser getUserByBaseinfoid(String baseinfoid);

    /**
     * 通过身份证获取会员表信息(自助机)
     *
     * @param aac002
     * @return 用户表
     * @throws Exception
     */
    SUser getUserByAac002(String aac002);

    SUser getUserByMobile(String mobile);
    
    SUser getUserByUserNameForGgfw(SUser user);
    
    int  addSUserForGgfw(SUser suser);
    
    Ac01 getAc01ByAac002ForGgfw(String aac002);
    
    Ab01 getAb01ByAab998ForGgfw(String aac002);
}
