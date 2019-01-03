package com.insigma.cloud.zuul.prc.admin;

import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.intercepter.FeignIntercepter;
import com.insigma.mvc.model.SErrorLog;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Headers("Content-Type:application/json")
@FeignClient(name = "api-auth", configuration = FeignIntercepter.class)
public interface LogService {
    @PostMapping("/api/errorlog/list")
    AjaxReturnMsg all(@RequestBody SErrorLog sErrorLog);
}
