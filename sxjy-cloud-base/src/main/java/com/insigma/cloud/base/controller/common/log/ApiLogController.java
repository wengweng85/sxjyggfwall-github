package com.insigma.cloud.base.controller.common.log;

import com.insigma.cloud.base.service.common.log.ApiLogService;
import com.insigma.cloud.common.context.SUserUtil;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.exception.AppException;
import com.insigma.mvc.model.SAppLog;
import com.insigma.mvc.model.SErrorLog;
import com.insigma.mvc.model.SLog;
import com.insigma.mvc.model.SUserLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    @PostMapping(value = "/errorlogs", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg getErrorLogList(@RequestBody SErrorLog sErrorLog) throws AppException {
        return AjaxReturnMsg.success(apiLogService.getErrorLogList(sErrorLog));
    }

    /**
     * 获取所有日志
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "获取所有异常日志", notes = "获取所有异常日志", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/errorlogs2", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg getErrorLogList2(@RequestBody SErrorLog sErrorLog) throws AppException {
        return AjaxReturnMsg.success(apiLogService.getErrorLogListByPage(sErrorLog));
    }


    /**
     * 通过日志编号获取异常日志明细信息
     * @return
     */
    @ApiOperation(value = "获取所有异常日志", notes = "获取所有异常日志", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value="/q_errorlog", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg getErrorLogById(@RequestBody SErrorLog sErrorLog) throws AppException {
        return AjaxReturnMsg.success(apiLogService.queryErrorLogById(sErrorLog.getLogid()));
    }

    /**
     * 通过日志编号获取异常日志明细信息
     * @return
     */
    @ApiOperation(value = "删除日志", notes = "删除日志", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value="/delete_errorlog", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg delete_errorlog(@RequestBody SErrorLog sErrorLog) throws AppException {
        return AjaxReturnMsg.success(apiLogService.deleteErrorLog(sErrorLog.getLogid()));
    }

    /**
     * 保存异常日志
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "保存异常日志", notes = "保存异常日志", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/errorlog", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg saveSErrorLog(@RequestBody SErrorLog sErrorLog) throws AppException {
        return AjaxReturnMsg.success(apiLogService.saveSErrorLog(sErrorLog));
    }


    /**
     * 保存业务日志
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "保存运行日志", notes = "保存运行日志", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/slog", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg saveSLog(@RequestBody SLog sLog) throws AppException {
        return AjaxReturnMsg.success(apiLogService.saveSLog(sLog));
    }

    /**
     * 保存渠道端访问日志
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "保存渠道端访问日志", notes = "保存渠道端访问日志", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/sapplog", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg saveSAppLog(@RequestBody SAppLog sAppLog) throws AppException {
        return AjaxReturnMsg.success(apiLogService.saveAppLog(sAppLog));
    }

    /**
     * 保存业务日志
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "保存用户操作日志", notes = "保存用户操作日志", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/userlog", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg saveUserLog(@RequestBody SUserLog sUserLog) throws AppException {
        sUserLog.setAae011(SUserUtil.getUserId());
        return AjaxReturnMsg.success(apiLogService.saveUserLog(sUserLog));
    }
}
