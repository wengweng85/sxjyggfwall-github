package com.insigma.cloud.common.intercepter;

import com.insigma.cloud.common.constants.CommonConstants;
import com.insigma.cloud.common.context.SUserUtil;
import com.insigma.cloud.common.utils.JwtUtils;
import com.insigma.mvc.model.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限过滤器 非必须
 * 如果header中没有token也可通过校验
 * @author admin
 */
public class AuthUnNecessaryIntercepter extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(AuthUnNecessaryIntercepter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String requestUri = request.getRequestURI();
        logger.debug("requestUri="+requestUri);
        String token = request.getHeader(CommonConstants.CONTEXT_AUTHORIZATION);
        if (null != token) {
            try {
                //截取掉"Bearer "
                AccessToken accessToken = JwtUtils.getInfoFromToken(token.substring(7, token.length()));
                SUserUtil.setToken(token);
                logger.debug("Thread.currentThread().getName()="+Thread.currentThread().getName());
                SUserUtil.setUsername(accessToken.getUsername());
                SUserUtil.setName(accessToken.getName());
                SUserUtil.setUserId(accessToken.getUserid());
            } catch (Exception e) {
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
}
