package com.insigma.cloud.base.dao.login;

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
public interface ApiLoginMapper {

    /**
     * ͨ���û�����ȡ��Ա����Ϣ
     *
     * @param username
     * @return �û���
     * @throws Exception
     */
    SUser getUserByUsername(@Param("username") String username);

    /**
     * ͨ��userid��ȡ��Ա����Ϣ
     *
     * @param userid
     * @return �û���
     * @throws Exception
     */
    SUser getUserByUserid(@Param("userid") String userid);

    /**
     * ͨ���û����������ȡ��Ա����Ϣ
     *
     * @param username
     * @param password
     * @return
     */
    SUser getUserByUsernamePass(@Param("username") String username, @Param("password") String password);

    /**
     * ͨ���û�id��ȡ�û���ɫ����
     *
     * @return ��ɫ����
     * @throws Exception
     */
    List<SRole> findRolesStr(String loginname);


    /**
     * ͨ���û�id��ȡ�û�Ȩ�޼���
     *
     * @return Ȩ�޼���
     * @throws Exception
     */
    List<SPermission> findPermissionStr(String loginname);

    /**
     * ����hashinfo
     *
     * @param inf
     */
    void saveLoginHashInfo(LoginInf inf);


    LoginInf findLoginInfoByhashcode(String loginhash);

    /**
     * ��¼��¼��Ϣ
     *
     * @param user
     */
    void updateLogintimes(SUser user);

    /**
     * ����openId��ȡ�ʺ���Ϣ
     *
     * @param openId
     * @return
     */
    SUser getSUserByOpenId(String openId);

    /**
     * ����openId
     *
     * @param user
     */
    void saveOpenId(SUser user);

    /**
     * ����baseinfoid��ȡ�û���Ϣ
     *
     * @param baseinfoid
     * @return
     */
    SUser getUserByBaseinfoid(String baseinfoid);

    /**
     * ͨ�����֤��ȡ��Ա����Ϣ(������)
     *
     * @param aac002
     * @return �û���
     * @throws Exception
     */
    SUser getUserByAac002(String aac002);

    SUser getUserByMobile(String mobile);
    
    SUser getUserByUserNameForGgfw(SUser user);
    
    int  addSUserForGgfw(SUser suser);
    
    Ac01 getAc01ByAac002ForGgfw(String aac002);
    
    Ab01 getAb01ByAab998ForGgfw(String aac002);
}
