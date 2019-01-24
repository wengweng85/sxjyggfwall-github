package com.insigma.cloud.auth.serviceimpl.token;

import com.insigma.cloud.auth.dao.user.ApiUserMapper;
import com.insigma.cloud.auth.service.token.ApiTokenService;
import com.insigma.cloud.common.utils.JwtUtils;
import com.insigma.mvc.model.AccessToken;
import com.insigma.mvc.model.SPermission;
import com.insigma.mvc.model.SRole;
import com.insigma.mvc.model.SUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ��¼service�ӿ�
 *
 * @author  admin
 */

@Service
public class ApiTokenServiceImpl implements ApiTokenService {

    private static Logger logger = LoggerFactory.getLogger(ApiTokenService.class);

    @Autowired
    private ApiUserMapper apiUserMapper;

    /**
     * ͨ���û�����ȡ��ɫ
     * @param username �û���
     */
    @Override
    public List<SRole> findRolesStr(String username) {
        List<SRole> list = apiUserMapper.findRolesStr(username);
        return list;
    }

    /**
     * ͨ���û�����ȡȨ��
     * @param username �û���
     */
    @Override
    public List<SPermission> findPermissionStr(String username) {
        List<SPermission> list = apiUserMapper.findPermissionStr(username);
        return list;
    }

    /**
     * ͨ���û�����ȡ�˵�
     * @param username �û���
     */
    @Override
    public List<SPermission> findMenuTree(String username) {
        List<SPermission> list = apiUserMapper.findPermissionStr(username);
        return filterPersmList(list);
    }


    /**
     * ͨ���û����������ȡjwt token
     * @param username �û���
     * @param password md5���ܺ������
     * @return <code>AccessToken</code>
     * @throws Exception
     */
    @Override
    public AccessToken getToken(String username,String password) throws Exception {
        AccessToken accessToken = null;
        SUser user = apiUserMapper.getUserByUsername(username, password);
        if (user != null) {
            apiUserMapper.updateLogintimes(user);
            //���û�jwt��������token
            accessToken = new AccessToken();
            accessToken.setUserid(user.getUserid());
            accessToken.setUsername(user.getUsername());
            accessToken.setName(user.getName());
            String token = JwtUtils.generateToken(accessToken);
            logger.debug("jwt=" + token);
            accessToken.setToken(token);
        }
        return accessToken;
    }

    /**
     * ͨ���û����������ȡjwt token
     * @param username �û���
     * @param password md5���ܺ������
     * @return <code>SUser</code>
     * @throws Exception
     */
    @Override
    public SUser getUser(String username,String password) throws Exception {
        AccessToken accessToken = null;
        SUser user = apiUserMapper.getUserByUsername(username, password);
        if (user != null) {
            apiUserMapper.updateLogintimes(user);
            //����ʱ��
            user.setExpires(System.currentTimeMillis() + JwtUtils.MAX_AGE);
            //���û�jwt��������token
            accessToken = new AccessToken();
            accessToken.setUserid(user.getUserid());
            accessToken.setUsername(user.getUsername());
            accessToken.setName(user.getName());
            String token = JwtUtils.generateToken(accessToken);
            logger.debug("jwt=" + token);
            user.setToken(token);
            //��������
            user.setPassword("");
        }
        return user;
    }


    /**
     * ���˲˵�
     * @param permlist
     */
    public static List<SPermission> filterPersmList(List< SPermission > permlist){
        List<SPermission> resultlist=new ArrayList<SPermission>();
        List<SPermission> firstTempPermlist=new ArrayList<SPermission>();
        //���˵���ť�ڵ�
        for(int i=0;i<permlist.size();i++) {
            if("3".equals(permlist.get(i).getPermtype())){
                permlist.remove(i);
                i--;
            }
        }

        //�Ƚ���һ���ڵ���˳���
        for(int i=0;i<permlist.size();i++) {
            //����ǵ�һ��
            if("0".equals(permlist.get(i).getParentid())||permlist.get(i).getParentid().matches("\\w{0,12}") ){
                firstTempPermlist.add(permlist.get(i));
                permlist.remove(i);
                i--;
            }
        }

        //�ٸ��ݵ�һ���ڵ���˳��ڶ������������ļ��ڵ�
        for(int i=0;i<firstTempPermlist.size();i++){
            SPermission firstTempPerm=firstTempPermlist.get(i);
            List<SPermission> secondPersmList=new ArrayList<SPermission>();
            for(int j=0;j<permlist.size();j++) {
                SPermission secondTempPerm=permlist.get(j);
                //�ڶ���
                if(secondTempPerm.getParentid().equals(firstTempPerm.getPermissionid())){
                    permlist.remove(j);
                    j--;
                    List<SPermission> thirdPermList=new ArrayList<SPermission>();
                    for(int k=0;k<permlist.size();k++){
                        SPermission thirdTempPerm = permlist.get(k);
                        //������
                        if(thirdTempPerm.getParentid().equals(secondTempPerm.getPermissionid())){
                            List<SPermission> fourthPermList = new ArrayList<SPermission>();
                            for(int h=0;h<permlist.size();h++) {
                                //���ļ�
                                if(permlist.get(h).getParentid().equals(thirdTempPerm.getPermissionid())) {
                                    fourthPermList.add(permlist.get(h));
                                }
                            }
                            thirdTempPerm.setChild(fourthPermList);
                            thirdPermList.add(thirdTempPerm);
                        }
                    }
                    secondTempPerm.setChild(thirdPermList);
                    secondPersmList.add(secondTempPerm);
                }
            }
            if(secondPersmList.size()>0){
                firstTempPerm.setChild(secondPersmList);
            }
            resultlist.add(firstTempPerm);
        }
        return resultlist;
    }

}
