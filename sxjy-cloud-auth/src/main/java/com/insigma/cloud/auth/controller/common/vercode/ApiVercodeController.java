package com.insigma.cloud.auth.controller.common.vercode;

import com.insigma.cloud.auth.service.common.svercord.ApiSvercordService;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SVerCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 发送手机验证码
 */
@RestController
@Api(description = "手机验证码")
public class ApiVercodeController  {
    
	@Resource
	private ApiSvercordService apiSvercordService;
    
    /**
     * 发送短信
     * @throws Exception
     */
	@ApiOperation(value = "发送短信", notes = "发送短信", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/api/person/vercode/send", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg send(@RequestBody SVerCode sVerCode) throws Exception {
    	 return AjaxReturnMsg.success(apiSvercordService.sendVerCode(sVerCode.getMobile()));
    }
    
}
