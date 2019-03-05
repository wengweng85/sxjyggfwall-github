package com.insigma.cloud.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.dto.SysCode;
import com.insigma.cloud.common.utils.JSONUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * 令牌桶限流
 */
public class RateLimitFilter extends ZuulFilter {

    private final static Logger logger = LoggerFactory.getLogger(RateLimitFilter.class);

    //令牌数
    private final static int RATELIMIT_NUM=1000;

    private static final RateLimiter rateLimit = RateLimiter.create(RATELIMIT_NUM);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        if(!rateLimit.tryAcquire()){
            logger.debug("超过流量{}/s,限流中",RATELIMIT_NUM);
            //不通过
            requestContext.setSendZuulResponse(false);
            setFailedRequest(AjaxReturnMsg.error(SysCode.SYS_REQUEST_LIMIT), 200);
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
            String result= JSONUtils.beanToJson(body);
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
}