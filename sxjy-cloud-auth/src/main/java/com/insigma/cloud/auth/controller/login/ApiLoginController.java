package com.insigma.cloud.auth.controller.login;

import com.insigma.cloud.auth.service.login.ApiLoginService;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Api(description = "��¼�ӿ�")
@RestController
public class ApiLoginController {


	@Resource
	private ApiLoginService apiLoginService;

    
	/**
     * �������֤�����ͳһ������ô����¼
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "�������֤�����ͳһ������ô����¼", notes = "�������֤�����ͳһ������ô����¼")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AjaxReturnMsg login(HttpServletRequest request, @RequestBody SUser suser_input) throws Exception {
      SUser suser=apiLoginService.login(request,suser_input);
      if(suser!=null){
          return AjaxReturnMsg.success(suser);
      }else{
          return AjaxReturnMsg.fail("�����ڵ��û�");
      }
    }
}
