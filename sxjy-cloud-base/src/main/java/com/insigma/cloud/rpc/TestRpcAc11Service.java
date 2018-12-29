package com.insigma.cloud.rpc;

import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.intercepter.FeignIntercepter;
import com.insigma.mvc.model.Ac11;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "api-sxjyjysy", configuration = FeignIntercepter.class)
public interface TestRpcAc11Service {
    @PostMapping("/api/ac11")
    AjaxReturnMsg test(@RequestBody Ac11 ac11);
}
