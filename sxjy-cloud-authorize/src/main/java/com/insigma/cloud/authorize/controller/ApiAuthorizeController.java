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

@Api(description = "�û���Ȩ")
@RestController
public class ApiAuthorizeController {

	@Resource
	private ApiAuthorizeService apiAuthorizeService;

    /**
     * ��ȡ�˵�
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "��ȡ�˵�", notes = "��ȡ�˵�")
    @PostMapping(value = "/menus", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg menus()throws Exception {
        List<SPermission> menuTree= apiAuthorizeService.findMenuTree(SUserUtil.getUsername());
        return AjaxReturnMsg.success(JSONArray.toJSON(menuTree));
    }

    /**
     * �����û�����ȡ�û���ɫ��Ȩ��
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "�����û�����ȡ�û���ɫ", notes = "�����û�����ȡ�û���ɫ",produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg roles() throws Exception {
        List<SRole> roleList= apiAuthorizeService.findRolesStr(SUserUtil.getUsername());
        return AjaxReturnMsg.success(roleList);
    }

    /**
     * �����û�����ȡ�û�Ȩ��
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "�����û�����ȡ�û�Ȩ��", notes = "�����û�����ȡ�û�Ȩ��",produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/permissions",produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg permissions() throws Exception {
        List<SPermission> permissionList= apiAuthorizeService.findPermissionStr(SUserUtil.getUsername());
        return AjaxReturnMsg.success(permissionList);
    }
}
