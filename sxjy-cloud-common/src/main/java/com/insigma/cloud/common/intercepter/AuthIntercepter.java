package com.insigma.cloud.common.intercepter;

import com.insigma.cloud.common.constants.CommonConstants;
import com.insigma.cloud.common.context.SUserUtil;
import com.insigma.cloud.common.utils.JWT;
import com.insigma.mvc.model.SUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthIntercepter extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(HandlerInterceptorAdapter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accesstoken = request.getHeader(CommonConstants.CONTEXT_TOKEN);
        logger.info("accesstoken="+accesstoken);
        SUser suser = JWT.unsign(accesstoken, SUser.class);
        SUserUtil.setCurrentUser(suser);
        logger.info("------current thread id-----"+Thread.currentThread().getId());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        SUserUtil.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
