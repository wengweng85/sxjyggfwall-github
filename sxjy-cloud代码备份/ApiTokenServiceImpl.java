package com.insigma.cloud.auth.serviceimpl.token;

import com.insigma.cloud.auth.dao.user.ApiUserMapper;
import com.insigma.cloud.auth.service.token.ApiTokenService;
import com.insigma.cloud.common.utils.JWT_Server;
import com.insigma.cloud.common.utils.JwtUtils;
import com.insigma.mvc.model.AccessToken;
import com.insigma.mvc.model.SPermission;
import com.insigma.mvc.model.SRole;
import com.insigma.mvc.model.SUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ��¼service�ӿ�
 *
 * @author xxx
 */

@Service
public class ApiTokenServiceImpl implements ApiTokenService {

    private static Logger logger = LoggerFactory.getLogger(ApiTokenService.class);

    @Autowired
    private ApiUserMapper apiUserMapper;

    /**
     * findRolesStr
     */
    @Override
    public List<SRole> findRolesStr(String username) {
        List<SRole> list = apiUserMapper.findRolesStr(username);
        return list;
    }

    /**
     * findPermissionStr
     */
    @Override
    public List<SPermission> findPermissionStr(String username)  {
        List<SPermission> list = apiUserMapper.findPermissionStr(username);
        return list;
    }

    /**
     * ��ȡtoken
     */
    /*@Override
    public AccessToken getToken(SUser suser) throws Exception {
        AccessToken accessToken=null;
        SUser user = apiUserMapper.getUserByUsername(suser.getUsername(),suser.getPassword());
        if(user!=null){
            apiUserMapper.updateLogintimes(user);
            //���û�jwt��������token
            accessToken=new AccessToken();

            //����ʱ��
            accessToken.setExpires(System.currentTimeMillis()+JWT_Server.MAX_AGE);
            String token = JWT_Server.sign(user);
            logger.debug("user="+token);
            accessToken.setToken(token);
        }
        return accessToken;
    }*/

    @Override
    public AccessToken getToken(SUser suser) throws Exception {
        AccessToken accessToken=null;
        SUser user = apiUserMapper.getUserByUsername(suser.getUsername(),suser.getPassword());
        if(user!=null){
            apiUserMapper.updateLogintimes(user);
            //���û�jwt��������token
            accessToken=new AccessToken();
            accessToken.setUserid(user.getUserid());
            accessToken.setUsername(user.getUsername());
            accessToken.setName(user.getName());
            //����ʱ��
            accessToken.setExpires(System.currentTimeMillis()+JWT_Server.MAX_AGE);
            String token = JwtUtils.generateToken(accessToken);
            logger.debug("jwt="+token);
            accessToken.setToken(token);
        }
        return accessToken;
    }


    /**
     * ˢ��token
     */
   /* @Override
    public AccessToken refreshToken(String jwt) throws Exception {
        AccessToken accessToken=null;
        //ɾ��ԭjwt
        SUser user=JWT_Server.unsign(jwt,SUser.class);
        //���û�jwt��������token
        accessToken=new AccessToken();
        //����ʱ��
        accessToken.setExpires(System.currentTimeMillis()+JWT_Server.MAX_AGE);
        String refreshToken = JWT_Server.sign(user);
        logger.debug("refreshToken="+refreshToken);
        accessToken.setToken(refreshToken);
        return accessToken;
    }*/
}
