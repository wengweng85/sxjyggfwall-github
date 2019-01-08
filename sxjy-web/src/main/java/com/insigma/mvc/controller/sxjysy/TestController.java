package com.insigma.mvc.controller.sxjysy;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insigma.http.HttpRequestUtils;
import com.insigma.mvc.APIURLConstraints;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.Ac11;
import com.insigma.resolver.AppException;

/**
 * 首页contoller
 *
 * @author xxx
 */
@Controller
public class TestController extends MvcHelper {

    //http工具类
    @Resource
    private HttpRequestUtils httpRequestUtils;

    /**
	 * codevalue 代码树
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws com.insigma.resolver.AppException
	 */
	@RequestMapping(value = "/ac11/detail/{id}")
	@ResponseBody
	public Ac11 treedata(HttpServletRequest request, HttpServletResponse response,@PathVariable String id) throws AppException {
	    HashMap map=new HashMap();
	    map.put("eec001", id);
	    Ac11 ac11 = (Ac11)httpRequestUtils.httpPostObject(APIURLConstraints.API_AC11_DETAIL, map,Ac11.class);
		return ac11;
	}
}
