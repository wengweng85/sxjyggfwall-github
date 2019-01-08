package com.insigma.cloud.zuul.filter;

import com.insigma.cloud.common.constants.CommonConstants;
import com.insigma.cloud.common.context.SUserUtil;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.utils.JSONUtils;
import com.insigma.cloud.common.utils.JWT_Server;
import com.insigma.cloud.common.utils.JwtUtils;
import com.insigma.mvc.model.AccessToken;
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
        return 1;
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
        String token = request.getHeader(CommonConstants.CONTEXT_TOKEN);
        logger.debug("token="+token);
        if(null == token || token == ""){
            token = request.getParameter(CommonConstants.TOKEN);
        }
        if (null == token) {
            setFailedRequest(AjaxReturnMsg.error403(), 200);
            return null;
        }
        try {
            //截取掉"Bearer "
            JwtUtils.getInfoFromToken(token.substring(7, token.length()));
            SUserUtil.setToken(token);
        } catch (Exception e) {
            setFailedRequest(AjaxReturnMsg.error404(), 200);
            return null;
        }
        return null;
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
