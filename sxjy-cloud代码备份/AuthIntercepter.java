package com.insigma.cloud.common.intercepter;

import com.insigma.cloud.common.constants.CommonConstants;
import com.insigma.cloud.common.context.SUserUtil;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.dto.UserToken;
import com.insigma.cloud.common.utils.JSONUtils;
import com.insigma.cloud.common.utils.JWT_Server;
import com.insigma.cloud.common.utils.JwtUtils;
import com.insigma.mvc.model.AccessToken;
import com.insigma.mvc.model.SUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.persistence.Access;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 权限过滤器
 */
public class AuthIntercepter extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(HandlerInterceptorAdapter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String requestUri = request.getRequestURI();

        logger.debug("requestUri="+requestUri);
        String token = request.getHeader(CommonConstants.CONTEXT_TOKEN);
        logger.debug("token="+token);
        if (null == token) {
            setFailedRequest(AjaxReturnMsg.error403(),response);
            return false;
        }
        try {
            //截取掉"Bearer "
            AccessToken accessToken = JwtUtils.getInfoFromToken(token.substring(7, token.length()));
            SUserUtil.setToken(token);
            logger.info("------设置token"+Thread.currentThread().getId());
            SUserUtil.setUsername(accessToken.getUsername());
            SUserUtil.setName(accessToken.getName());
            SUserUtil.setUserID(accessToken.getUserid());
        } catch (Exception e) {
            setFailedRequest(AjaxReturnMsg.error404(), response);
            return false;
        }

        return super.preHandle(request, response, handler);
    }


    //@Override
    public boolean preHandle_old(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String requestUri = request.getRequestURI();

        logger.debug("requestUri="+requestUri);
        String accessToken = request.getHeader(CommonConstants.CONTEXT_TOKEN);
        logger.debug("accessToken="+accessToken);
        if (null == accessToken) {
            setFailedRequest(AjaxReturnMsg.error403(),response);
            return false;
        }
        try {
            //截取掉"Bearer "
            SUser suser = JWT_Server.unsign(accessToken.substring(7, accessToken.length()), SUser.class);
            suser.setToken(accessToken);
            SUserUtil.setCurrentUser(suser);
        } catch (Exception e) {
            setFailedRequest(AjaxReturnMsg.error404(), response);
            return false;
        }
        logger.debug("current user id="+SUserUtil.getCurrentUser().getUsername());
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
            String result= JSONUtils.beanToJson(AjaxReturnMsg.error403());
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
