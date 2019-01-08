package com.insigma.cloud.auth.controller.common.vercode;

import com.insigma.cloud.auth.service.common.codevalidator.ApiCodeValidatorService;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SVerCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 发送手机验证码
 */
@RestController
@Api(description = "手机验证码")
public class ApiVercodeController  {
    
	@Resource
	private ApiCodeValidatorService apiCodeValidatorService;
    
    /**
     * 发送短信
     * @throws Exception
     */
	@ApiOperation(value = "发送短信", notes = "发送短信")
    @RequestMapping(value = "/api/person/vercode/send", method = RequestMethod.POST)
    public AjaxReturnMsg send(@RequestBody  SVerCode sVerCode) throws Exception {
    	 return AjaxReturnMsg.success(apiCodeValidatorService.sendVerCode(sVerCode.getMobile()));
    }
    
}
