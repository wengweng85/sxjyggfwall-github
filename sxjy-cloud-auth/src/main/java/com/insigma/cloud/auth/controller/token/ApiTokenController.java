package com.insigma.cloud.auth.controller.token;

import com.insigma.cloud.auth.service.token.ApiTokenService;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
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
import javax.servlet.http.HttpServletRequest;
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
     * ˢ��token
     * @return
     * @throws Exception
     */
    /*@ApiOperation(value = "ˢ��token", notes = "ˢ��token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
    public AjaxReturnMsg refreshToken( @RequestBody AccessToken accessToken) throws Exception {
        AccessToken refreshToken=apiLoginService.refreshToken(accessToken.getToken());
        return AjaxReturnMsg.success(refreshToken);
    }*/


    /**
     * �����û�����ȡ�û���ɫ��Ȩ��
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "�����û�����ȡ�û���ɫ", notes = "�����û�����ȡ�û���ɫ")
    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public AjaxReturnMsg roleandpermission(HttpServletRequest request, @RequestBody SUser suser) throws Exception {
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
    public AjaxReturnMsg permissions(HttpServletRequest request, @RequestBody SUser suser) throws Exception {
        List<SPermission> permissionList=apiLoginService.findPermissionStr (suser.getUsername());
        return AjaxReturnMsg.success(permissionList);
    }
}
