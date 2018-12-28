package com.insigma.cloud.common.service;


import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.dto.LogDO;
import com.insigma.cloud.common.intercepter.FeignIntercepter;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;

@Headers("Content-Type:application/json")
@FeignClient(name = "api-base", configuration = FeignIntercepter.class)
public interface LogRpcService {
    @Async
    @PostMapping("log/save")
    AjaxReturnMsg save(LogDO logDO);
}
