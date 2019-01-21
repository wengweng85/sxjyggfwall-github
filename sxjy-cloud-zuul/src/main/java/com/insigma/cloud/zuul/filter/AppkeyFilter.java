package com.insigma.cloud.zuul.filter;

import com.insigma.cloud.common.constants.CommonConstants;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.utils.JSONUtils;
import com.insigma.cloud.rpc.LogRpcService;
import com.insigma.cloud.zuul.service.channel.ApiChannelService;
import com.insigma.mvc.model.SAppLog;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * appkey过滤器
 * @Author admin
 */
public class AppkeyFilter extends ZuulFilter {

    private final static Logger logger = LoggerFactory.getLogger(AppkeyFilter.class);

    private String ignorePath = "/api-auth/cas";

    @Autowired
    private ApiChannelService apiChannelService;

    @Autowired
    private LogRpcService logRpcService;

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
        //判断哪些地址不检验appkey
        if (isStartWith(requestUri)) {
            return null;
        }
        String appkey = request.getParameter(CommonConstants.CONTEXT_APPKEY);
        logger.debug("appkey={}",appkey);
        if (null == appkey) {
            setFailedRequest(AjaxReturnMsg.error401(), 200);
            return null;
        }
        if(!isUrlValid(requestUri,appkey)){
            setFailedRequest(AjaxReturnMsg.error408(), 200);
            return null;
        }

        try{
            //异步保存渠道端访问日志
            SAppLog sAppLog=new SAppLog();
            sAppLog.setAppkey(appkey);
            sAppLog.setUrl(requestUri);
            logRpcService.saveSAppLog(sAppLog);
        }catch (Exception ex){

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

    /**
     * 当前渠道是否有访问对应微服务权限
     * @param appkey
     * @param url
     * @return
     */
    private boolean isUrlValid(String url,String appkey){
        return apiChannelService.isUrlValid(url,appkey);
    }
}
