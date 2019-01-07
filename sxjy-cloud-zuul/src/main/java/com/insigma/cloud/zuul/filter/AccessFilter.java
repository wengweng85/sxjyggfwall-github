package com.insigma.cloud.zuul.filter;

import com.insigma.cloud.common.constants.CommonConstants;
import com.insigma.cloud.common.context.SUserUtil;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.utils.JSONUtils;
import com.insigma.cloud.common.utils.JWT_Server;
import com.insigma.mvc.model.SUser;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

/**
 * accesfilter
 * @Author admin
 */
public class AccessFilter extends ZuulFilter {

    private final static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    private String ignorePath = "/api-auth";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10000;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }


    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        final String requestUri = request.getRequestURI();
        logger.debug("requestUri="+requestUri);
        if (isStartWith(requestUri)) {
            return null;
        }
        String accessToken = request.getHeader(CommonConstants.CONTEXT_TOKEN);
        logger.debug("accessToken="+accessToken);
        if(null == accessToken || accessToken == ""){
            accessToken = request.getParameter(CommonConstants.TOKEN);
        }
        if (null == accessToken) {
            setFailedRequest(AjaxReturnMsg.error40003(), 200);
            return null;
        }
        try {
            //截取掉"bearer "
            SUser suser = JWT_Server.unsign(accessToken.substring(7, accessToken.length()), SUser.class);
            SUserUtil.setCurrentUser(suser);
        } catch (Exception e) {
            setFailedRequest(AjaxReturnMsg.error40004(), 200);
            return null;
        }
        SUserUtil.setToken(accessToken);
        Set<String> headers = (Set<String>) ctx.get("ignoredHeaders");
        //We need our JWT_Server tokens relayed to resource servers
        //添加自己header
//        ctx.addZuulRequestHeader(CommonConstants.CONTEXT_TOKEN, accessToken);
        //移除忽略token
        headers.remove("authorization");
        return null;
//        RequestContext ctx = RequestContext.getCurrentContext();
//        Set<String> headers = (Set<String>) ctx.get("ignoredHeaders");
//        // We need our JWT_Server tokens relayed to resource servers
//        headers.remove("authorization");
//        return null;
    }

    /**
     * setFailedRequest
     * @param body
     * @param code
     */
    private void setFailedRequest(Object body, int code) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        HttpServletResponse response = ctx.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try{
            out = response.getWriter();
            String result=JSONUtils.beanToJson(body);
            System.out.println("result->"+result);
            out.write(result);
            out.flush();
        }catch(IOException e){
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
        ctx.setSendZuulResponse(false);
    }

    /**
     * isStartWith
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        for (String s : ignorePath.split(",")) {
            if (requestUri.startsWith(s)) {
                flag= true;
                break;
            }
        }
        return flag;
    }
}
