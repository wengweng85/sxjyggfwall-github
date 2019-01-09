package com.insigma.cloud.zuul.filter;

import com.insigma.cloud.common.constants.CommonConstants;
import com.insigma.cloud.common.context.SUserUtil;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.rsa.SignUtils;
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
import java.util.Date;
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
        //校验签名
        String signature=request.getParameter(CommonConstants.SIGN_SIGNATURE);
        String timestamp=request.getParameter(CommonConstants.SIGN_TIMESTAMP);
        String nonce=request.getParameter(CommonConstants.SIGN_NONCE);
        logger.debug("signature=%s,timestamp=%s,nonce=%s",signature,timestamp,nonce);
        if(null!=signature&&null!=timestamp&&null!=nonce){
            if(SignUtils.checkSignature(signature,timestamp,nonce)){
                //计算接口时间是否过期
                Date nowTime = new Date(System.currentTimeMillis());
                Date timestamp_Time= new Date(new Long(timestamp));
                logger.debug("nowTime :" + nowTime.toLocaleString());
                logger.debug("timestamp_Time :" + timestamp_Time.toLocaleString());
                if(timestamp_Time.after(nowTime)){
                    logger.debug("requestUri="+requestUri);
                    //判断哪些地址不检验token
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
                    return null;
                }else{
                    setFailedRequest(AjaxReturnMsg.error500("请求已过期"), 200);
                    return null;
                }
            }else{
                setFailedRequest(AjaxReturnMsg.error500("非法请求,验签失败"), 200);
                return null;
            }
        }else{
            setFailedRequest(AjaxReturnMsg.error500("签名参数为空或缺失"), 200);
            return null;
        }
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
