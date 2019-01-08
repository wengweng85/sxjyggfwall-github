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

@Api(description = "用户认证")
@RestController
public class ApiTokenController {


	@Resource
	private ApiTokenService apiLoginService;

    
	/**
     * 根据用户名密码获取认证token
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取认证token", notes = "获取认证token")
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public AjaxReturnMsg gettoken(@RequestBody SUser suser) throws Exception {
      AccessToken accessToken=apiLoginService.getToken(suser);
      if(accessToken!=null){
          return AjaxReturnMsg.success(accessToken);
      }else{
          return AjaxReturnMsg.fail("认证失败");
      }
    }

    /**
     * 刷新token
     * @return
     * @throws Exception
     */
    /*@ApiOperation(value = "刷新token", notes = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
    public AjaxReturnMsg refreshToken( @RequestBody AccessToken accessToken) throws Exception {
        AccessToken refreshToken=apiLoginService.refreshToken(accessToken.getToken());
        return AjaxReturnMsg.success(refreshToken);
    }*/


    /**
     * 根据用户名获取用户角色及权限
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据用户名获取用户角色", notes = "根据用户名获取用户角色")
    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public AjaxReturnMsg roleandpermission(HttpServletRequest request, @RequestBody SUser suser) throws Exception {
        List<SRole> roleList=apiLoginService.findRolesStr(suser.getUsername());
        return AjaxReturnMsg.success(roleList);
    }

    /**
     * 根据用户名获取用户权限
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据用户名获取用户权限", notes = "根据用户名获取用户权限")
    @RequestMapping(value = "/permissions", method = RequestMethod.POST)
    public AjaxReturnMsg permissions(HttpServletRequest request, @RequestBody SUser suser) throws Exception {
        List<SPermission> permissionList=apiLoginService.findPermissionStr (suser.getUsername());
        return AjaxReturnMsg.success(permissionList);
    }
}
