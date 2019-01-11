package com.insigma.cloud.zuul.filter;

import com.insigma.cloud.common.constants.CommonConstants;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.dto.SysCode;
import com.insigma.cloud.common.rsa.SignUtils;
import com.insigma.cloud.common.utils.JSONUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * SignatrueFilter 签名验证过滤器
 * @Author admin
 */
public class SignatrueFilter extends ZuulFilter {

    private final static Logger logger = LoggerFactory.getLogger(SignatrueFilter.class);


    @Resource
    private RedisTemplate<String, String> redisTemplate;

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
        //校验签名
        String signature=request.getParameter(CommonConstants.SIGN_SIGNATURE);
        String timestamp=request.getParameter(CommonConstants.SIGN_TIMESTAMP);
        String nonce=request.getParameter(CommonConstants.SIGN_NONCE);
        logger.debug(String.format("signature:%s,timestamp:%s,nonce:%s",signature,timestamp,nonce));
        if(null!=signature&&null!=timestamp&&null!=nonce){
            if(SignUtils.checkSignature(signature,timestamp,nonce)){
                //判断此签名是否出现,做幂等设计
                String redis_sign=redisTemplate.opsForValue().get(signature);
                if(redis_sign==null){
                    //5分钟过期。因为5分钟后签名过期
                    redisTemplate.opsForValue().set(signature,"1",SignUtils.TIMESTAMP, TimeUnit.MILLISECONDS);
                    //计算接口时间是否过期
                    Date nowTime = new Date(System.currentTimeMillis());
                    Date timestamp_Time= new Date(new Long(timestamp));
                    logger.debug("nowTime :{} timestamp_Time : {}" ,nowTime.toLocaleString(),timestamp_Time.toLocaleString());
                    if(timestamp_Time.after(nowTime)){
                        return null;
                    }else{
                        setFailedRequest(AjaxReturnMsg.error(SysCode.SYS_SIGN_TIMESTAMP_EXPIRE), 200);
                        return null;
                    }
                }else{
                    setFailedRequest(AjaxReturnMsg.error500("重复的请求") , 200);
                    return null;
                }
            }else{
                setFailedRequest(AjaxReturnMsg.error(SysCode.SYS_SIGN_ERROR), 200);
                return null;
            }
        }else{
            setFailedRequest(AjaxReturnMsg.error(SysCode.SYS_SIGN_PARAM_EMPTY), 200);
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
