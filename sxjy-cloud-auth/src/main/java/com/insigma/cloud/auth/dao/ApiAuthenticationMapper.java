package com.insigma.cloud.auth.dao;

import com.insigma.mvc.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ��¼service�ӿ�
 *
 * @author  admin
 */
@Mapper
public interface ApiAuthenticationMapper  {

    /**
     * ͨ���û�����ȡ��Ա����Ϣ
     *
     * @param username
     * @param password
     * @return �û���
     * @throws Exception
     */
    SUser getUserByUsername(@Param("username") String username, @Param("password") String password);

    /**
     * ��¼��¼��Ϣ
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
