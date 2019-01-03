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

@Api(description = "登录接口")
@RestController
public class ApiLoginController {


	@Resource
	private ApiLoginService apiLoginService;

    
	/**
     * 根据身份证号码或统一社会信用代码登录
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据身份证号码或统一社会信用代码登录", notes = "根据身份证号码或统一社会信用代码登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AjaxReturnMsg login(HttpServletRequest request, @RequestBody SUser suser_input) throws Exception {
      SUser suser=apiLoginService.login(request,suser_input);
      if(suser!=null){
          return AjaxReturnMsg.success(suser);
      }else{
          return AjaxReturnMsg.fail("不存在的用户");
      }
    }
}
