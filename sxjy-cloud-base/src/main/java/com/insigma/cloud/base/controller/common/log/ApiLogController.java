package com.insigma.cloud.base.controller.common.log;

import com.insigma.cloud.base.service.common.log.ApiLogService;
import com.insigma.cloud.common.context.SUserUtil;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.exception.AppException;
import com.insigma.mvc.model.SErrorLog;
import com.insigma.mvc.model.SLog;
import com.insigma.mvc.model.SUserLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 日志记录
 * @author admin
 *
 */
@RestController
@Api(description = "日志记录")
public class ApiLogController  {

    @Resource
    private ApiLogService apiLogService;

    /**
     * 获取所有日志
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "获取所有异常日志", notes = "获取所有异常日志", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/errorlogs")
    public AjaxReturnMsg getErrorLogList(@RequestBody SErrorLog sErrorLog) throws AppException {
        return AjaxReturnMsg.success(apiLogService.getErrorLogList(sErrorLog));
    }

    /**
     * 保存异常日志
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "保存异常日志", notes = "保存异常日志", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/errorlog")
    public AjaxReturnMsg saveSErrorLog(@RequestBody SErrorLog sErrorLog) throws AppException {
        return AjaxReturnMsg.success(apiLogService.saveSErrorLog(sErrorLog));
    }


    /**
     * 保存业务日志
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "保存运行日志", notes = "保存运行日志", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/slog")
    public AjaxReturnMsg saveSLog(@RequestBody SLog sLog) throws AppException {
        return AjaxReturnMsg.success(apiLogService.saveSLog(sLog));
    }

    /**
     * 保存业务日志
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "保存用户操作日志", notes = "保存用户操作日志", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/userlog")
    public AjaxReturnMsg saveUserLog(@RequestBody SUserLog sUserLog) throws AppException {
        sUserLog.setAae011(SUserUtil.getUserId());
        return AjaxReturnMsg.success(apiLogService.saveUserLog(sUserLog));
    }
}
