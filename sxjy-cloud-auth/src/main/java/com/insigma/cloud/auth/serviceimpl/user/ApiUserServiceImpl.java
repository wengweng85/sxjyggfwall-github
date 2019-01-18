package com.insigma.cloud.auth.serviceimpl.user;

import com.insigma.cloud.auth.dao.user.ApiUserMapper;
import com.insigma.cloud.auth.service.token.ApiTokenService;
import com.insigma.cloud.auth.service.user.ApiUserService;
import com.insigma.mvc.model.Ab01;
import com.insigma.mvc.model.Ac01;
import com.insigma.mvc.model.SUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * �û���¼ע����ط���
 *
 * @author  admin
 */

@Service
public class ApiUserServiceImpl implements ApiUserService {

    private static Logger logger = LoggerFactory.getLogger(ApiTokenService.class);

    @Autowired
    private ApiUserMapper apiUserMapper;

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
            Ac01 ac01=apiUserMapper.getAc01ByAac002ForGgfw(suser.getUsername());
            //�������ac01��û�������Ա��Ϣ
            if(ac01!=null){
                baseinfoid=ac01.getAac001();
            }
            suser.setBaseinfoid(baseinfoid);
            SUser tempsuer=apiUserMapper.getUserByUserNameForGgfw(suser);
            if(tempsuer!=null){
                return tempsuer;
            }else{
                int addnum= apiUserMapper.addSUserForGgfw(suser);
                apiUserMapper.getUserByUserNameForGgfw(suser);
            }
        }
        if("2".equals(usetype)){ //��λ�û���½
            Ab01 ab01=apiUserMapper.getAb01ByAab998ForGgfw(suser.getUsername());
            //�������ab01��û�������Ա��Ϣ
            if(ab01!=null){
                baseinfoid=ab01.getAab001();
            }
            suser.setBaseinfoid(baseinfoid);
            SUser tempsuer=apiUserMapper.getUserByUserNameForGgfw(suser);
            if(tempsuer!=null){
                return tempsuer;
            }else{
                int addnum= apiUserMapper.addSUserForGgfw(suser);
                return apiUserMapper.getUserByUserNameForGgfw(suser);
            }
        }
        return null;
    }
}
