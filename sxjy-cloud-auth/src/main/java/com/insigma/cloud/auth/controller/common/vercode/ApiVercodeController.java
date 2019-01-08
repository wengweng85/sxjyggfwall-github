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
 * �����ֻ���֤��
 */
@RestController
@Api(description = "�ֻ���֤��")
public class ApiVercodeController  {
    
	@Resource
	private ApiCodeValidatorService apiCodeValidatorService;
    
    /**
     * ���Ͷ���
     * @throws Exception
     */
	@ApiOperation(value = "���Ͷ���", notes = "���Ͷ���")
    @RequestMapping(value = "/api/person/vercode/send", method = RequestMethod.POST)
    public AjaxReturnMsg send(@RequestBody  SVerCode sVerCode) throws Exception {
    	 return AjaxReturnMsg.success(apiCodeValidatorService.sendVerCode(sVerCode.getMobile()));
    }
    
}
