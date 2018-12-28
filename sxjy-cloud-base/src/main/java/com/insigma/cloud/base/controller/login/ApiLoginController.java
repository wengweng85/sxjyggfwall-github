package com.insigma.cloud.base.controller.login;

import com.insigma.cloud.base.service.common.svercord.ApiSvercordService;
import com.insigma.cloud.base.service.login.ApiLoginService;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SUser;
import com.insigma.mvc.model.Svercord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(description = "登录接口")
@RestController
@RequestMapping("/api/login")
public class ApiLoginController {

	@Resource
	private ApiSvercordService svercordService;
	
	
	@Resource
	private ApiLoginService apiLoginService;
	
	/**
     * 根据 mobile 查询手机验证码
     * //@param mobile
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据 mobile 查询手机验证码", notes = "根据 mobile 查询手机验证码")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public AjaxReturnMsg list(@RequestBody Svercord svercord) throws Exception {
        String mobile = svercord.getMobile();
        if(!"".equals(mobile) && mobile != null){
            return AjaxReturnMsg.success(svercordService.querySvercordListByMobile(mobile));
        }
        return null;
    }
    /**
     * 
     * @Title: random
     * @Description: 保存短信驗證碼
     * @author and
     * @date   2018年9月13日
     * @return  
     * @return svercord     
     * @throws
     */
    @ApiOperation(value = "保存短信驗證碼信息", notes = "保存短信驗證碼信息")
    @RequestMapping(value = "/addSvercord", method = RequestMethod.POST)
    public AjaxReturnMsg random(@RequestBody Svercord svercord){
    	 int insertSelective =0;
    	 if(!"".equals(svercord) && svercord != null){
    	   insertSelective = svercordService.insertSelective(svercord);
    	 }
    	 return AjaxReturnMsg.success(insertSelective);
    }
    
	/**
     * 根据身份证号码或统一社会信用代码登录
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据身份证号码或统一社会信用代码登录", notes = "根据身份证号码或统一社会信用代码登录")
    @RequestMapping(value = "/idcard", method = RequestMethod.POST)
    public AjaxReturnMsg idcard(@RequestBody SUser suser) throws Exception {
      return AjaxReturnMsg.success(apiLoginService.getUserByIdForGgfw(suser));
    }
    
}
