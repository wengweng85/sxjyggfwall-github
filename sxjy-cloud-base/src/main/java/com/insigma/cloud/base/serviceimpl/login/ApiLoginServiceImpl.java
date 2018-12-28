package com.insigma.cloud.base.serviceimpl.login;

import com.insigma.cloud.base.dao.login.ApiLoginMapper;
import com.insigma.cloud.base.service.common.codevalidator.ApiCodeValidatorService;
import com.insigma.cloud.base.service.login.ApiLoginService;
import com.insigma.cloud.common.utils.IPUtils;
import com.insigma.cloud.common.utils.JWT;
import com.insigma.mvc.model.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ��¼service�ӿ�
 *
 * @author xxx
 */

@Service
@Transactional
public class ApiLoginServiceImpl  implements ApiLoginService {

    @Resource
    private ApiLoginMapper apiloginmapper;
   
    @Resource
    private ApiCodeValidatorService codeValidatorService;

    /**
     * getUserByUsername
     */
    @Override
    public SUser getUserByUsername(String username) {
        SUser suser = apiloginmapper.getUserByUsername(username);
        return suser;
    }
    /**
     * getUserByUsername
     */
    @Override
    public SUser getUserInfoByUsername(String username) throws Exception{
        SUser suser = apiloginmapper.getUserByUsername(username);
        return suser;
    }

    /**
     * ͨ���û������ȡ��Ա����Ϣ
     * @param userid
     * @return
     * @throws Exception
     */
    @Override
    public SUser getUserByUserid(String userid) throws Exception {
        SUser suser = apiloginmapper.getUserByUserid(userid);
        //�ж��Ƿ�������
        suser.setIssetpwd(!StringUtils.isEmpty(suser.getPassword()));
        suser.setPassword("");
        return suser;
    }


    /**
     * findRolesStr
     */
    @Override
    public List<SRole> findRolesStr(String loginname) {
        List<SRole> list = apiloginmapper.findRolesStr(loginname);
        return list;
    }

    /**
     * findPermissionStr
     */
    @Override
    public List<SPermission> findPermissionStr(String loginname)  {
        List<SPermission> list = apiloginmapper.findPermissionStr(loginname);
        return list;
    }

    /**
     * saveLoginHashInfo
     */
    @Override
    public void saveLoginHashInfo(LoginInf inf) {
        apiloginmapper.saveLoginHashInfo(inf);
    }

    /**
     * findLoginInfoByhashcode
     */
    @Override
    public LoginInf findLoginInfoByhashcode(String loginhash) {
        return apiloginmapper.findLoginInfoByhashcode(loginhash);
    }

    /**
     * �û���¼
     */
    @Override
    public SUser login(HttpServletRequest request, SUser suser) throws Exception {
      
        SUser user = apiloginmapper.getUserByUsernamePass(suser.getUsername(), suser.getPassword());
      
        if (StringUtils.isNotEmpty(suser.getOpenId())) {
            user.setOpenId(suser.getOpenId());
            apiloginmapper.saveOpenId(user);
        }
        if (StringUtils.isEmpty(suser.getLastloginip())) {
            String ip = IPUtils.getIpAddr(request);
            suser.setLastloginip(ip);
        }

        //��¼��¼��־
        user.setLastloginip(suser.getLastloginip());
        apiloginmapper.updateLogintimes(user);

        //���û�jwt��������token
        String token = JWT.sign(user);
        System.out.println("token="+token);
        user.setToken(token);

        return user;
     
    }

	@Override
	public SUser getUserByIdForGgfw(SUser suser) {
		String baseinfoid="";
		String usetype=suser.getUsertype();
		if("1".equals(usetype)){ //�����û���½
			Ac01 ac01=apiloginmapper.getAc01ByAac002ForGgfw(suser.getUsername());
			//�������ac01��û�������Ա��Ϣ
			if(ac01!=null){
				baseinfoid=ac01.getAac001();
			}
			suser.setBaseinfoid(baseinfoid);
			SUser tempsuer=apiloginmapper.getUserByUserNameForGgfw(suser);
			if(tempsuer!=null){
                return tempsuer;
			}else{
				int addnum= apiloginmapper.addSUserForGgfw(suser);
				apiloginmapper.getUserByUserNameForGgfw(suser);
			}
		}
		if("2".equals(usetype)){ //��λ�û���½
			
			Ab01 ab01=apiloginmapper.getAb01ByAab998ForGgfw(suser.getUsername());
			//�������ab01��û�������Ա��Ϣ
			if(ab01!=null){
				baseinfoid=ab01.getAab001();
			}
			suser.setBaseinfoid(baseinfoid);
			SUser tempsuer=apiloginmapper.getUserByUserNameForGgfw(suser);
			if(tempsuer!=null){
				return tempsuer;
			}else{
				int addnum= apiloginmapper.addSUserForGgfw(suser);
				return apiloginmapper.getUserByUserNameForGgfw(suser);
			}
			
		}
		return null;
	}
}
