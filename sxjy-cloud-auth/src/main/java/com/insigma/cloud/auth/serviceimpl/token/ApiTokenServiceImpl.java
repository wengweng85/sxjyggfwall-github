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

import java.util.ArrayList;
import java.util.List;

/**
 * 登录service接口
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
    public List<SPermission> findPermissionStr(String username) {
        List<SPermission> list = apiUserMapper.findPermissionStr(username);
        return list;
    }

    /**
     * findPermissionStr
     */
    @Override
    public List<SPermission> findMenuTree(String username) {
        List<SPermission> list = apiUserMapper.findPermissionStr(username);
        return filterPersmList(list);
    }


    @Override
    public AccessToken getToken(SUser suser) throws Exception {
        AccessToken accessToken = null;
        SUser user = apiUserMapper.getUserByUsername(suser.getUsername(), suser.getPassword());
        if (user != null) {
            apiUserMapper.updateLogintimes(user);
            //给用户jwt加密生成token
            accessToken = new AccessToken();
            accessToken.setUserid(user.getUserid());
            accessToken.setUsername(user.getUsername());
            accessToken.setName(user.getName());
            //过期时间
            accessToken.setExpires(System.currentTimeMillis() + JWT_Server.MAX_AGE);
            String token = JwtUtils.generateToken(accessToken);
            logger.debug("jwt=" + token);
            accessToken.setToken(token);
        }
        return accessToken;
    }


    /**
     * 过滤菜单
     * @param permlist
     */
    public static List<SPermission> filterPersmList(List< SPermission > permlist){
        List<SPermission> resultlist=new ArrayList<SPermission>();
        List<SPermission> firstTempPermlist=new ArrayList<SPermission>();

        //过滤掉按钮节点
        for(int i=0;i<permlist.size();i++) {
            if(permlist.get(i).getPermtype().equals("3")){
                permlist.remove(i);
                i--;
            }
        }

        //先将第一级节点过滤出来
        for(int i=0;i<permlist.size();i++) {
            //如果是第一级
            if(permlist.get(i).getParentid().equals("0")||permlist.get(i).getParentid().matches("\\w{0,12}") ){
                firstTempPermlist.add(permlist.get(i));
                permlist.remove(i);
                i--;
            }
        }

        //再根据第一级节点过滤出第二级或三级或四级节点
        for(int i=0;i<firstTempPermlist.size();i++){
            SPermission firstTempPerm=firstTempPermlist.get(i);
            List<SPermission> secondPersmList=new ArrayList<SPermission>();
            for(int j=0;j<permlist.size();j++) {
                SPermission secondTempPerm=permlist.get(j);
                //第二级
                if(secondTempPerm.getParentid().equals(firstTempPerm.getPermissionid())){
                    permlist.remove(j);
                    j--;
                    List<SPermission> thirdPermList=new ArrayList<SPermission>();
                    for(int k=0;k<permlist.size();k++){
                        SPermission thirdTempPerm = permlist.get(k);
                        //第三级
                        if(thirdTempPerm.getParentid().equals(secondTempPerm.getPermissionid())){
                            List<SPermission> fourthPermList = new ArrayList<SPermission>();
                            for(int h=0;h<permlist.size();h++) {
                                //第四级
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
