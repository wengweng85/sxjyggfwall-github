package com.insigma.cloud.rpc;

import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.intercepter.FeignIntercepter;
import com.insigma.mvc.model.SErrorLog;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;


@Headers("Content-Type:application/json")
@FeignClient(name = "api-base", configuration = FeignIntercepter.class)
public interface LogRpcService {

    @PostMapping("errorlog")
    AjaxReturnMsg save(SErrorLog sErrorLog);
}
