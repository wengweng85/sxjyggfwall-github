package com.insigma.cloud.auth.serviceimpl;

import com.insigma.cloud.auth.dao.ApiAuthenticationMapper;
import com.insigma.cloud.auth.service.ApiAuthenticationService;
import com.insigma.cloud.common.utils.JwtUtils;
import com.insigma.mvc.model.*;
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
public class ApiAuthenticationServiceImpl implements ApiAuthenticationService {

    private static Logger logger = LoggerFactory.getLogger(ApiAuthenticationService.class);

    @Autowired
    private ApiAuthenticationMapper apiAuthenticationMapper;


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
        SUser user = apiAuthenticationMapper.getUserByUsername(username, password);
        if (user != null) {
            apiAuthenticationMapper.updateLogintimes(user);
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
        SUser user = apiAuthenticationMapper.getUserByUsername(username, password);
        if (user != null) {
            apiAuthenticationMapper.updateLogintimes(user);
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
     * getUserByIdForGgfw
     * @param suser
     * @return
     */
    @Override
    public SUser getUserByIdForGgfw(SUser suser) {
        String baseinfoid="";
        String usetype=suser.getUsertype();
        if("1".equals(usetype)){ //�����û���½
            Ac01 ac01=apiAuthenticationMapper.getAc01ByAac002ForGgfw(suser.getUsername());
            //�������ac01��û�������Ա��Ϣ
            if(ac01!=null){
                baseinfoid=ac01.getAac001();
            }
            suser.setBaseinfoid(baseinfoid);
            SUser tempsuer=apiAuthenticationMapper.getUserByUserNameForGgfw(suser);
            if(tempsuer!=null){
                return tempsuer;
            }else{
                int addnum= apiAuthenticationMapper.addSUserForGgfw(suser);
                apiAuthenticationMapper.getUserByUserNameForGgfw(suser);
            }
        }
        if("2".equals(usetype)){ //��λ�û���½
            Ab01 ab01=apiAuthenticationMapper.getAb01ByAab998ForGgfw(suser.getUsername());
            //�������ab01��û�������Ա��Ϣ
            if(ab01!=null){
                baseinfoid=ab01.getAab001();
            }
            suser.setBaseinfoid(baseinfoid);
            SUser tempsuer=apiAuthenticationMapper.getUserByUserNameForGgfw(suser);
            if(tempsuer!=null){
                return tempsuer;
            }else{
                int addnum= apiAuthenticationMapper.addSUserForGgfw(suser);
                return apiAuthenticationMapper.getUserByUserNameForGgfw(suser);
            }
        }
        return null;
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
