package com.insigma.cloud.common.intercepter;

import com.insigma.cloud.common.constants.CommonConstants;
import com.insigma.cloud.common.context.SUserUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeignIntercepter implements RequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(FeignIntercepter.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {
        logger.debug("Thread.currentThread().getName()="+Thread.currentThread().getName());
        if(null!=SUserUtil.getToken()){
            requestTemplate.header(CommonConstants.CONTEXT_TOKEN, SUserUtil.getToken());
        }
    }
}