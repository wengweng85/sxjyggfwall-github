package com.insigma.cloud.auth.serviceimpl.login;

import com.insigma.cloud.auth.dao.login.ApiLoginMapper;
import com.insigma.cloud.auth.service.login.ApiLoginService;
import com.insigma.cloud.common.utils.IPUtils;
import com.insigma.cloud.common.utils.JWT;
import com.insigma.mvc.model.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 登录service接口
 *
 * @author xxx
 */

@Service
public class ApiLoginServiceImpl  implements ApiLoginService {

    @Autowired
    private ApiLoginMapper apiLoginMapper;

    /**
     * getUserByUsername
     */
    @Override
    public SUser getUserByUsername(String username) {
        SUser suser = apiLoginMapper.getUserByUsername(username);
        return suser;
    }

    /**
     * findRolesStr
     */
    @Override
    public List<SRole> findRolesStr(String username) {
        List<SRole> list = apiLoginMapper.findRolesStr(username);
        return list;
    }

    /**
     * findPermissionStr
     */
    @Override
    public List<SPermission> findPermissionStr(String username)  {
        List<SPermission> list = apiLoginMapper.findPermissionStr(username);
        return list;
    }

    /**
     * saveLoginHashInfo
     */
    @Override
    public void saveLoginHashInfo(LoginInf inf) {
        apiLoginMapper.saveLoginHashInfo(inf);
    }

    /**
     * findLoginInfoByhashcode
     */
    @Override
    public LoginInf findLoginInfoByhashcode(String loginhash) {
        return apiLoginMapper.findLoginInfoByhashcode(loginhash);
    }

    /**
     * 用户登录
     */
    @Override
    public SUser login(HttpServletRequest request, SUser suser) throws Exception {
        SUser user = apiLoginMapper.getUserByUsername(suser.getUsername());
        if(user!=null){
            if (StringUtils.isEmpty(suser.getLastloginip())) {
                String ip = IPUtils.getIpAddr(request);
                suser.setLastloginip(ip);
            }
            //记录登录日志
            user.setLastloginip(suser.getLastloginip());
            apiLoginMapper.updateLogintimes(user);

            //给用户jwt加密生成token
            String token = JWT.sign(user);
            System.out.println("token="+token);
            user.setToken(token);
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
        if("1".equals(usetype)){ //个人用户登陆
            Ac01 ac01=apiLoginMapper.getAc01ByAac002ForGgfw(suser.getUsername());
            //如果发现ac01中没有相关人员信息
            if(ac01!=null){
                baseinfoid=ac01.getAac001();
            }
            suser.setBaseinfoid(baseinfoid);
            SUser tempsuer=apiLoginMapper.getUserByUserNameForGgfw(suser);
            if(tempsuer!=null){
                return tempsuer;
            }else{
                int addnum= apiLoginMapper.addSUserForGgfw(suser);
                apiLoginMapper.getUserByUserNameForGgfw(suser);
            }
        }
        if("2".equals(usetype)){ //单位用户登陆
            Ab01 ab01=apiLoginMapper.getAb01ByAab998ForGgfw(suser.getUsername());
            //如果发现ab01中没有相关人员信息
            if(ab01!=null){
                baseinfoid=ab01.getAab001();
            }
            suser.setBaseinfoid(baseinfoid);
            SUser tempsuer=apiLoginMapper.getUserByUserNameForGgfw(suser);
            if(tempsuer!=null){
                return tempsuer;
            }else{
                int addnum= apiLoginMapper.addSUserForGgfw(suser);
                return apiLoginMapper.getUserByUserNameForGgfw(suser);
            }
        }
        return null;
    }
}
