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
 * ��ҳcontoller
 *
 * @author xxx
 */
@Controller
public class IndexController extends MvcHelper {

    //http������
    @Resource
    private HttpRequestUtils httpRequestUtils;

    /**
     * ��������ҳ��
     * @param request
     * @return
     */
    @PostMapping("/")
    public ModelAndView gotoAdminIndex(HttpServletRequest request, Model model) throws Exception {
        ModelAndView modelAndView=new ModelAndView("index/admin_index");
        modelAndView.addObject("SYS_TITLE", "��������ƽ̨");
        modelAndView.addObject("suser", SUserUtil.getCurrentUser());
        modelAndView.addObject("permlist",request.getSession().getAttribute(SUserUtil.SHIRO_CURRENT_PERM_LIST_INFO));
        return modelAndView;
    }


    /**
     * http 404 ����
     *
     * @return
     */
    @PostMapping("/404")
    public String error404() {
        return "error/404";
    }

    /**
     * http 500 ����
     *
     * @return
     */
    @PostMapping("/500")
    public String error500() {
        return "error/500";
    }

    /**
     * δ��Ȩ
     *
     * @return
     */
    @PostMapping("/unrecognized")
    public String unrecognized() {
        return "error/unrecognized";
    }
}
