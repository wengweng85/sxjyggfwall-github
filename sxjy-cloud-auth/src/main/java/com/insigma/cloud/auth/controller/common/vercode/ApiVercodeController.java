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
 * �����ֻ���֤��
 */
@RestController
@Api(description = "�ֻ���֤��")
public class ApiVercodeController  {
    
	@Resource
	private ApiSvercordService apiSvercordService;
    
    /**
     * ���Ͷ���
     * @throws Exception
     */
	@ApiOperation(value = "���Ͷ���", notes = "���Ͷ���", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/api/person/vercode/send", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg send(@RequestBody SVerCode sVerCode) throws Exception {
    	 return AjaxReturnMsg.success(apiSvercordService.sendVerCode(sVerCode.getMobile()));
    }
    
}
