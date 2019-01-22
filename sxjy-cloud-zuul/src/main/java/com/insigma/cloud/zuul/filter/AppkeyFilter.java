package com.insigma.cloud.zuul.filter;

import com.insigma.cloud.common.constants.CommonConstants;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.guava.CacheMap;
import com.insigma.cloud.common.utils.JSONUtils;
import com.insigma.cloud.rpc.LogRpcService;
import com.insigma.mvc.model.SAppLog;
import com.insigma.mvc.model.SysApiChannel;
import com.insigma.mvc.model.SysApiInterface;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * appkey过滤器
 * @Author admin
 */
public class AppkeyFilter extends ZuulFilter {

    private final static Logger logger = LoggerFactory.getLogger(AppkeyFilter.class);

    private String ignorePath = "/api-auth/cas";

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
        // 微服务编号
        String interface_id= getInterfaceId(requestUri,appkey);
        if(interface_id.equals("")){
            setFailedRequest(AjaxReturnMsg.error408(), 200);
            return null;
        }

        try{
            //异步保存渠道端访问日志
            SAppLog sAppLog=new SAppLog();
            sAppLog.setInterface_id(interface_id);
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
     * @return interface_id 空代表没有匹配到
     */
    private String getInterfaceId(String url, String appkey){
        String interface_id="";
        SysApiChannel sysApiChannel=(SysApiChannel)CacheMap.get(appkey);
        if(sysApiChannel!=null){
            List<SysApiInterface> sysApiInterfaceList= sysApiChannel.getUrls();
            for(int i=0;i<sysApiInterfaceList.size();i++){
                if(url.startsWith(sysApiInterfaceList.get(i).getInterfaceUrl())){
                    logger.debug("currentUrl={}",sysApiInterfaceList.get(i).getInterfaceUrl());
                    interface_id=sysApiInterfaceList.get(i).getInterfaceId();
                    break;
                }
            }
        }
        return interface_id;
    }
}
