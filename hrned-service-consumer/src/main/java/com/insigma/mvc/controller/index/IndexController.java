package com.insigma.mvc.controller.index;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.insigma.common.struct.Data;
import com.insigma.rpc.service.QueryPersonInfo1Service;
import com.insigma.rpc.service.QueryPersonInfo2Service;
import com.insigma.rpc.service.QueryPersonInfo3Service;
import com.insigma.rpc.service.QueryPersonInfo4Service;
import com.insigma.rpc.service.QueryPersonInfo5Service;


/**
 * 工作网首页contoller
 * @author admin
 *
 */
@Controller
public class IndexController  {
	 
	
	@Resource 
	private QueryPersonInfo1Service queryPersonInfo1Service;
	
	@Resource 
	private QueryPersonInfo2Service queryPersonInfo2Service;
	
	@Resource 
	private QueryPersonInfo3Service queryPersonInfo3Service;
	
	@Resource 
	private QueryPersonInfo4Service queryPersonInfo4Service;
	
	@Resource 
	private QueryPersonInfo5Service queryPersonInfo5Service;
	
	/**
	 * 管理功能主页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView gotoAdminIndex(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("index/index");
        return modelAndView;
	}
	
	/**
	 * 2.2.1	查询就业登记状态和就业形式接口 测试
	 * @param request
	 * @return
	 */
	@RequestMapping("/dubbotest1")
    @ResponseBody
    public String dubbotest1() {
		Data data=new Data("{\"aac002\":\"110101197009102564\",\"aac003\":\"赵梦\"}");
        System.out.println(data.beanToJson());
        String request=data.beanToJson();
        String response=queryPersonInfo1Service.exec(request);
		return response;
    }
	
	/**
	 * 2.2.2	查询职业资格等级、专业技术职务和学历接口测试
	 * @param request
	 * @return
	 */
	@RequestMapping("/dubbotest2")
    @ResponseBody
    public String dubbotest2() {
		Data data=new Data("{\"aac002\":\"110101197009102564\",\"aac003\":\"赵梦\"}");
        System.out.println(data.beanToJson());
        String request=data.beanToJson();
        String response=queryPersonInfo2Service.exec(request);
		return response;
    }
	
	/**
	 * 2.2.3	查询就业援助对象的类型和就业援助对象认定日期接口
	 * @param request
	 * @return
	 */
	@RequestMapping("/dubbotest3")
    @ResponseBody
    public String dubbotest3() {
		Data data=new Data("{\"aac002\":\"110101197009102564\",\"aac003\":\"赵梦\"}");
        System.out.println(data.beanToJson());
        String request=data.beanToJson();
        String response=queryPersonInfo3Service.exec(request);
		return response;
    }
	
	/**
	 * 2.2.4	查询享受政策类型接口
	 * @param request
	 * @return
	 */
	@RequestMapping("/dubbotest4")
    @ResponseBody
    public String dubbotest4() {
		Data data=new Data("{\"aac002\":\"110101197009102564\",\"aac003\":\"赵梦\"}");
        System.out.println(data.beanToJson());
        String request=data.beanToJson();
        String response=queryPersonInfo4Service.exec(request);
		return response;
    }
	
	/**
	 * 2.2.5	查询发证机构和发证日期接口
	 * @param request
	 * @return
	 */
	@RequestMapping("/dubbotest5")
    @ResponseBody
    public String dubbotest5() {
		Data data=new Data("{\"aac002\":\"110101197009102564\",\"aac003\":\"赵梦\",\"adc001\":\"1101010416000136\"}");
        System.out.println(data.beanToJson());
        String request=data.beanToJson();
        String response=queryPersonInfo5Service.exec(request);
		return response;
    }
}
