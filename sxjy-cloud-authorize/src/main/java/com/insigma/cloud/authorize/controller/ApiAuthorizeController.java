package com.insigma.cloud.authorize.controller;

import com.alibaba.fastjson.JSONArray;
import com.insigma.cloud.authorize.service.ApiAuthorizeService;
import com.insigma.cloud.common.context.SUserUtil;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SPermission;
import com.insigma.mvc.model.SRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(description = "用户授权")
@RestController
public class ApiAuthorizeController {

	@Resource
	private ApiAuthorizeService apiAuthorizeService;

    /**
     * 获取菜单
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取菜单", notes = "获取菜单")
    @PostMapping(value = "/menus", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg menus()throws Exception {
        List<SPermission> menuTree= apiAuthorizeService.findMenuTree(SUserUtil.getUsername());
        return AjaxReturnMsg.success(JSONArray.toJSON(menuTree));
    }

    /**
     * 根据用户名获取用户角色及权限
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据用户名获取用户角色", notes = "根据用户名获取用户角色",produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg roles() throws Exception {
        List<SRole> roleList= apiAuthorizeService.findRolesStr(SUserUtil.getUsername());
        return AjaxReturnMsg.success(roleList);
    }

    /**
     * 根据用户名获取用户权限
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据用户名获取用户权限", notes = "根据用户名获取用户权限",produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/permissions",produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg permissions() throws Exception {
        List<SPermission> permissionList= apiAuthorizeService.findPermissionStr(SUserUtil.getUsername());
        return AjaxReturnMsg.success(permissionList);
    }
}
