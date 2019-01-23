package com.insigma.cloud.zuul.filter;

import com.insigma.cloud.common.constants.CommonConstants;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.utils.JSONUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * jwt过滤器
 * @Author admin
 */
public class JwtFilter extends ZuulFilter {

    private final static Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    private String ignorePath = "/api-auth,/api-cata";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 3;
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
        //判断哪些地址不检验token
        if (isStartWith(requestUri)) {
            return null;
        }
        String token = request.getHeader(CommonConstants.CONTEXT_AUTHORIZATION)==null?"":
                request.getHeader(CommonConstants.CONTEXT_AUTHORIZATION).replaceAll(CommonConstants.CONTEXT_BEARE,"")
                .trim();
        logger.debug("token="+token);
        if(null==token||token.equals("null")||token.equals("")){
            setFailedRequest(AjaxReturnMsg.error403(), HttpStatus.OK.value());
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
