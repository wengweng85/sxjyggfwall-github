package com.insigma.cloud.base.controller.demo;

import com.insigma.cloud.base.service.demo.ApiDemoService;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.mvc.model.DemoAc01;
import com.insigma.mvc.model.DemoAc01Group;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * demo测试程序
 * @author admin
 *
 */
@RestController
@Api(description = "测试")
@RequestMapping("/demo")
public class ApiDemoController  {
	
	@Resource
	private ApiDemoService apidemoService;
	
	/**
	 * 获取人员列表
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "获取人员列表", produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value="/ac01s", produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg  getAc01List(@RequestBody DemoAc01 ac01 ) throws Exception {
		return AjaxReturnMsg.success(apidemoService.getDemoAc01List(ac01));
	}
	
	
	/**
	 * 根据id删除人员
	 * @return
	 */
	@ApiOperation(value = "根据id删除人员", produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value="/ac01/del",produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg deleteDemoDataById(@RequestBody DemoAc01 ac01){
		int i=  apidemoService.deleteDemoById(ac01.getId());
		if(i==1){
			return AjaxReturnMsg.success();
		}else{
			return AjaxReturnMsg.fail("失败");
		}
	}
	
	/**
	 * 根据id批量删除人员
	 * @param ac01
	 * @return
	 */
	@ApiOperation(value = "根据id批量删除人员", produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value="/ac01/delete/bat", produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg batDeleteDemodata(@RequestBody DemoAc01 ac01){
		int i=  apidemoService.batDeleteDemoData(ac01);
		return AjaxReturnMsg.success();
	}
	
	

	/**
	 * 根据id获取人员信息
	 * @return
	 */
	@ApiOperation(value = "根据id获取人员信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value="/ac01/detail", produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg getDemoById(@RequestBody DemoAc01 ac01) throws Exception {
		return AjaxReturnMsg.success(apidemoService.getDemoById(ac01.getId()));
	}
	
	/**
	 * 根据id获取人员信息
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "根据id获取人员信息返回中文", produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value="/ac01/name", produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg getDemoNameById(@RequestBody DemoAc01 ac01) throws Exception {
		return AjaxReturnMsg.success(apidemoService.getDemoNameById(ac01.getId()));
	}

	
	/**
	 * @param ac01
	 * @return
	 */
	@ApiOperation(value = "新增或更新人员", produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value="/ac01/put" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg savedata( @RequestBody DemoAc01 ac01, BindingResult result) throws Exception {
		//检验输入
		if (result.hasErrors()){
			return AjaxReturnMsg.validate(result);
		}
		apidemoService.saveDemoData(ac01);
		return AjaxReturnMsg.success();
	}

}
