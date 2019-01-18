package com.insigma.mvc.controller.index;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.insigma.common.util.SUserUtil;
import com.insigma.http.HttpRequestUtils;
import com.insigma.mvc.MvcHelper;

/**
 * 首页contoller
 *
 * @author  admin
 */
@Controller
public class IndexController extends MvcHelper {

    //http工具类
    @Resource
    private HttpRequestUtils httpRequestUtils;

    /**
     * 管理功能主页面
     * @param request
     * @return
     */
    @PostMapping("/")
    public ModelAndView gotoAdminIndex(HttpServletRequest request, Model model) throws Exception {
        ModelAndView modelAndView=new ModelAndView("index/admin_index");
        modelAndView.addObject("SYS_TITLE", "公共服务平台");
        modelAndView.addObject("suser", SUserUtil.getCurrentUser());
        modelAndView.addObject("permlist",request.getSession().getAttribute(SUserUtil.SHIRO_CURRENT_PERM_LIST_INFO));
        return modelAndView;
    }


    /**
     * http 404 错误
     *
     * @return
     */
    @PostMapping("/404")
    public String error404() {
        return "error/404";
    }

    /**
     * http 500 错误
     *
     * @return
     */
    @PostMapping("/500")
    public String error500() {
        return "error/500";
    }

    /**
     * 未授权
     *
     * @return
     */
    @PostMapping("/unrecognized")
    public String unrecognized() {
        return "error/unrecognized";
    }
}
