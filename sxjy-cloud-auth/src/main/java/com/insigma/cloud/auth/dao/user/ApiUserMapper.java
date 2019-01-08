package com.insigma.cloud.auth.dao.user;

import com.insigma.mvc.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ��¼service�ӿ�
 *
 * @author xxx
 */
@Mapper
public interface ApiUserMapper {

    /**
     * ͨ���û�����ȡ��Ա����Ϣ
     *
     * @param username
     * @return �û���
     * @throws Exception
     */
    SUser getUserByUsername(@Param("username") String username, @Param("password") String password);


    /**
     * ͨ���û�id��ȡ�û���ɫ����
     *
     * @return ��ɫ����
     * @throws Exception
     */
    List<SRole> findRolesStr(String username);


    /**
     * ͨ���û�id��ȡ�û�Ȩ�޼���
     *
     * @return Ȩ�޼���
     * @throws Exception
     */
    List<SPermission> findPermissionStr(String username);


    /**
     * ��¼��¼��Ϣ
     *
     * @param user
     */
    void updateLogintimes(SUser user);

    SUser getUserByUserNameForGgfw(SUser user);
    
    int  addSUserForGgfw(SUser suser);
    
    Ac01 getAc01ByAac002ForGgfw(String aac002);
    
    Ab01 getAb01ByAab998ForGgfw(String aac002);
}
