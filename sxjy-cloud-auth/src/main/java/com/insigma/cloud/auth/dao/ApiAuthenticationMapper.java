package com.insigma.cloud.auth.dao;

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
public interface ApiAuthenticationMapper  {

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
