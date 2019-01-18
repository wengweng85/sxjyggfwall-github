package com.insigma.cloud.base.controller.common.suggest;

import com.insigma.cloud.base.service.common.suggest.ApiSuggestSearchService;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SysSuggestKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 建议搜索controller
 */
@RestController
@Api(description = "建议搜索")
@RequestMapping(value = "/api")
public class ApiSuggestSearchController  {



    @Resource
    private ApiSuggestSearchService suggestSearchService;

   /**
    * 根据类型建议搜索
    */
    @ApiOperation(value = "根据类型建议搜索", notes = "根据类型建议搜索", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/common/suggest/searchcode", produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg searchcodebykey(HttpServletRequest request, HttpServletResponse response, @RequestBody SysSuggestKey key) throws Exception {
		return AjaxReturnMsg.success(suggestSearchService.searchByKey(key));
	}

}
