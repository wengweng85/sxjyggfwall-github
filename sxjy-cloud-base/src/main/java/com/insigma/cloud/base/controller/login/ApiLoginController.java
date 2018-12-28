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

@Api(description = "��¼�ӿ�")
@RestController
@RequestMapping("/api/login")
public class ApiLoginController {

	@Resource
	private ApiSvercordService svercordService;
	
	
	@Resource
	private ApiLoginService apiLoginService;
	
	/**
     * ���� mobile ��ѯ�ֻ���֤��
     * //@param mobile
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "���� mobile ��ѯ�ֻ���֤��", notes = "���� mobile ��ѯ�ֻ���֤��")
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
     * @Description: ���������C�a
     * @author and
     * @date   2018��9��13��
     * @return  
     * @return svercord     
     * @throws
     */
    @ApiOperation(value = "���������C�a��Ϣ", notes = "���������C�a��Ϣ")
    @RequestMapping(value = "/addSvercord", method = RequestMethod.POST)
    public AjaxReturnMsg random(@RequestBody Svercord svercord){
    	 int insertSelective =0;
    	 if(!"".equals(svercord) && svercord != null){
    	   insertSelective = svercordService.insertSelective(svercord);
    	 }
    	 return AjaxReturnMsg.success(insertSelective);
    }
    
	/**
     * �������֤�����ͳһ������ô����¼
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "�������֤�����ͳһ������ô����¼", notes = "�������֤�����ͳһ������ô����¼")
    @RequestMapping(value = "/idcard", method = RequestMethod.POST)
    public AjaxReturnMsg idcard(@RequestBody SUser suser) throws Exception {
      return AjaxReturnMsg.success(apiLoginService.getUserByIdForGgfw(suser));
    }
    
}
