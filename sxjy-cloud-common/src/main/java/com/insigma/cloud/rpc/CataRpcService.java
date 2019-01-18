package com.insigma.cloud.rpc;

import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.intercepter.FeignIntercepter;
import com.insigma.mvc.model.catalogue.ServiceDesk;
import feign.Headers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by wengsh on 2019/1/18.
 */
@Api(description = "远程调用服务-事项服务")
@Headers("Content-Type:application/json")
@FeignClient(name = "api-cata", configuration = FeignIntercepter.class)
public interface CataRpcService {

    @ApiOperation(value = "启动事项", notes = "启动事项", produces = MediaType.APPLICATION_JSON_VALUE)
    //@Async
    @PostMapping("catalogue/startCataDesk")
    AjaxReturnMsg startCataDesk(ServiceDesk serviceDesk);

    @ApiOperation(value = "更新事项状态", notes = "更新事项状态", produces = MediaType.APPLICATION_JSON_VALUE)
    //@Async
    @PostMapping("catalogue/updateCataDesk")
    AjaxReturnMsg updateCataDesk(ServiceDesk serviceDesk);
}
