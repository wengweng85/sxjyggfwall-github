package com.insigma.cloud.rpc;

import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.intercepter.FeignIntercepter;
import com.insigma.mvc.model.SAppLog;
import com.insigma.mvc.model.SErrorLog;
import com.insigma.mvc.model.SLog;
import com.insigma.mvc.model.SUserLog;
import feign.Headers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 远程调用服务-事项服务
 */
@Api(description = "远程调用服务-事项服务")
@Headers("Content-Type:application/json")
@FeignClient(name = "api-base", configuration = FeignIntercepter.class)
public interface LogRpcService {

    @ApiOperation(value = "保存异常日志", notes = "保存异常日志", produces = MediaType.APPLICATION_JSON_VALUE)
    @Async
    @PostMapping("errorlog")
    AjaxReturnMsg saveSErrLog(SErrorLog sErrorLog);

    @ApiOperation(value = "保存运行日志", notes = "保存运行日志", produces = MediaType.APPLICATION_JSON_VALUE)
    @Async
    @PostMapping("slog")
    AjaxReturnMsg saveSLog(SLog sLog);

    @ApiOperation(value = "保存用户操作日志", notes = "保存用户操作日志", produces = MediaType.APPLICATION_JSON_VALUE)
    @Async
    @PostMapping("userlog")
    AjaxReturnMsg saveSUserLog(SUserLog sUserLog);


    @ApiOperation(value = "保存渠道端访问日志", notes = "保存渠道端访问日志", produces = MediaType.APPLICATION_JSON_VALUE)
    @Async
    @PostMapping("sapplog")
    AjaxReturnMsg saveSAppLog(SAppLog sAppLog);
}
