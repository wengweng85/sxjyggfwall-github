package com.insigma.cloud.authorize.dao;

import com.insigma.mvc.model.SPermission;
import com.insigma.mvc.model.SRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ��Ȩ����
 *
 * @author  admin
 */
@Mapper
public interface ApiAuthorizeMapper {


    /**
     * ͨ���û�id��ȡ�û���ɫ����
     *
     * @param username
     * @return ��ɫ����
     * @throws Exception
     */
    List<SRole> findRolesStr(String username);


    /**
     * ͨ���û�id��ȡ�û�Ȩ�޼���
     *
     * @param  username
     * @return Ȩ�޼���
     * @throws Exception
     */
    List<SPermission> findPermissionStr(String username);

}
