package com.insigma.cloud.zuul.filter;


import com.insigma.cloud.common.constants.CommonConstants;
import com.insigma.cloud.common.context.FilterContextHandler;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.dto.UserToken;
import com.insigma.cloud.common.utils.JSONUtils;
import com.insigma.cloud.common.utils.JwtUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.logging.Logger;

/**
 * accesfilter
 * @Author admin
 */
public class AccessFilter extends ZuulFilter {

    private Logger logger = Logger.getLogger(AccessFilter.class.toString());

    private String ignorePath = "/api-base";

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
        logger.info("requestUri="+requestUri);
        if (isStartWith(requestUri)) {
            return null;
        }
        String accessToken = request.getHeader(CommonConstants.CONTEXT_TOKEN);
        if(null == accessToken || accessToken == ""){
            accessToken = request.getParameter(CommonConstants.TOKEN);
        }
        if (null == accessToken) {
            setFailedRequest(AjaxReturnMsg.error40003(), 200);
            return null;
        }
        try {
            UserToken userToken = JwtUtils.getInfoFromToken(accessToken);
        } catch (Exception e) {
            setFailedRequest(AjaxReturnMsg.error40004(), 200);
            return null;
        }
        FilterContextHandler.setToken(accessToken);
        Set<String> headers = (Set<String>) ctx.get("ignoredHeaders");
        //We need our JWT tokens relayed to resource servers
        //添加自己header
//        ctx.addZuulRequestHeader(CommonConstants.CONTEXT_TOKEN, accessToken);
        //移除忽略token
        headers.remove("authorization");
        return null;
//        RequestContext ctx = RequestContext.getCurrentContext();
//        Set<String> headers = (Set<String>) ctx.get("ignoredHeaders");
//        // We need our JWT tokens relayed to resource servers
//        headers.remove("authorization");
//        return null;
    }

    private void setFailedRequest(Object body, int code) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        HttpServletResponse response = ctx.getResponse();
        PrintWriter out = null;
        try{
            out = response.getWriter();
            out.write(JSONUtils.beanToJson(body));
            out.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
        ctx.setSendZuulResponse(false);
    }

    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        for (String s : ignorePath.split(",")) {

            if (requestUri.startsWith(s)) {
                return true;
            }
        }
        return true;
    }
}
