package com.insigma.cloud.base.controller.common.suggest;

import com.insigma.cloud.base.service.common.suggest.ApiSuggestSearchService;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SysSuggestKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ��������controller
 */
@RestController
@Api(description = "��������")
@RequestMapping(value = "/api")
public class ApiSuggestSearchController  {


    Log log = LogFactory.getLog(ApiSuggestSearchController.class);

    @Resource
    private ApiSuggestSearchService suggestSearchService;
    
 
	
   /**
    * �������ͽ�������
    */
    @ApiOperation(value = "�������ͽ�������", notes = "�������ͽ�������", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/common/suggest/searchcode")
	public AjaxReturnMsg searchcodebykey(HttpServletRequest request, HttpServletResponse response, @RequestBody SysSuggestKey key) throws Exception {
		return AjaxReturnMsg.success(suggestSearchService.searchByKey(key));
	}

}
