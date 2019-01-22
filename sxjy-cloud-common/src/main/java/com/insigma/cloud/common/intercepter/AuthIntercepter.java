package com.insigma.cloud.common.intercepter;

import com.insigma.cloud.common.constants.CommonConstants;
import com.insigma.cloud.common.context.SUserUtil;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.utils.JSONUtils;
import com.insigma.cloud.common.utils.JwtUtils;
import com.insigma.mvc.model.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 权限过滤器
 */
public class AuthIntercepter extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(AuthIntercepter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String requestUri = request.getRequestURI();
        logger.debug("requestUri="+requestUri);
        String token = request.getHeader(CommonConstants.CONTEXT_TOKEN);
        //logger.debug("token="+token);
        if (null == token) {
            setFailedRequest(AjaxReturnMsg.error403(),response);
            return false;
        }else{
            try {
                //截取掉"Bearer "
                AccessToken accessToken = JwtUtils.getInfoFromToken(token.substring(7, token.length()));
                SUserUtil.setToken(token);
                logger.debug("Thread.currentThread().getName()="+Thread.currentThread().getName());
                SUserUtil.setUsername(accessToken.getUsername());
                SUserUtil.setName(accessToken.getName());
                SUserUtil.setUserId(accessToken.getUserid());
            } catch (Exception e) {
                setFailedRequest(AjaxReturnMsg.error404(), response);
                return false;
            }
        }
        return super.preHandle(request, response, handler);
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.debug("------------------------------afterCompletion------------------------------------");
        SUserUtil.remove();
        super.afterCompletion(request, response, handler, ex);
    }

    /**
     * setFailedRequest
     * @param body
     */
    private void setFailedRequest(Object body,  HttpServletResponse response) {
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

    }
}
