package com.insigma.cloud.auth.controller.token;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONArray;
import com.insigma.cloud.auth.service.token.ApiTokenService;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.rsa.MD5Util;
import com.insigma.mvc.model.AccessToken;
import com.insigma.mvc.model.SPermission;
import com.insigma.mvc.model.SRole;
import com.insigma.mvc.model.SUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Api(description = "�û���֤")
@RestController
public class ApiTokenController {


	@Resource
	private ApiTokenService apiLoginService;


    /**
     * �����û��������ȡ��֤token
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "��ȡ��֤token", notes = "��ȡ��֤token")
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public AjaxReturnMsg gettoken(@RequestBody SUser suser) throws Exception {
        AccessToken accessToken=apiLoginService.getToken(suser);
        if(accessToken!=null){
            return AjaxReturnMsg.success(accessToken);
        }else{
            return AjaxReturnMsg.fail("��֤ʧ��");
        }
    }

    /**
     * ��ȡ��֤token���˵�
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "��ȡ��֤token���˵�", notes = "��ȡ��֤token���˵�")
    @RequestMapping(value = "/tokenmenus", method = RequestMethod.POST)
    public AjaxReturnMsg tokenAndmenus(@RequestBody SUser suser) throws Exception {
        AccessToken accessToken=apiLoginService.getToken(suser);
        if(accessToken!=null){
            List<SPermission> menuTree=apiLoginService.findMenuTree(suser.getUsername());
            HashMap map=new HashMap();
            map.put("token",accessToken);
            map.put("menus", JSONArray.toJSON(menuTree));
            AjaxReturnMsg ajaxReturnMsg= AjaxReturnMsg.success(map);
            return ajaxReturnMsg;
        }else{
            return AjaxReturnMsg.fail("��֤ʧ��");
        }
    }

    /**
     * ��ȡ�˵�
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "��ȡ�˵�", notes = "��ȡ�˵�")
    @RequestMapping(value = "/menus", method = RequestMethod.POST)
    public AjaxReturnMsg menus(@RequestBody SUser suser) throws Exception {
        List<SPermission> menuTree=apiLoginService.findMenuTree(suser.getUsername());
        return AjaxReturnMsg.success(menuTree);
    }

    /**
     * �����û�����ȡ�û���ɫ��Ȩ��
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "�����û�����ȡ�û���ɫ", notes = "�����û�����ȡ�û���ɫ")
    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public AjaxReturnMsg roles(@RequestBody SUser suser) throws Exception {
        List<SRole> roleList=apiLoginService.findRolesStr(suser.getUsername());
        return AjaxReturnMsg.success(roleList);
    }

    /**
     * �����û�����ȡ�û�Ȩ��
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "�����û�����ȡ�û�Ȩ��", notes = "�����û�����ȡ�û�Ȩ��")
    @RequestMapping(value = "/permissions", method = RequestMethod.POST)
    public AjaxReturnMsg permissions(@RequestBody SUser suser) throws Exception {
        List<SPermission> permissionList=apiLoginService.findPermissionStr(suser.getUsername());
        return AjaxReturnMsg.success(permissionList);
    }

}
