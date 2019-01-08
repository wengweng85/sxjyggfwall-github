package com.insigma.cloud.base.controller.common.log;

import com.insigma.cloud.base.service.common.log.ApiLogService;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.exception.AppException;
import com.insigma.mvc.model.SErrorLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ��־��¼
 * @author admin
 *
 */
@RestController
@Api(description = "��־��¼")
public class ApiLogController  {

    @Resource
    private ApiLogService apiLogService;

    /**
     * ��ȡ������־
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "��ȡ�����쳣��־", notes = "��ȡ�����쳣��־", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/errorlogs")
    public AjaxReturnMsg list(@RequestBody SErrorLog sErrorLog) throws AppException {
        return AjaxReturnMsg.success(apiLogService.getErrorLogList(sErrorLog));
    }

    /**
     * ��¼��־
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "�����쳣��־", notes = "�����쳣��־", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/errorlog")
    public AjaxReturnMsg add(@RequestBody SErrorLog sErrorLog) throws AppException {
        return AjaxReturnMsg.success(apiLogService.addSysErrorLog(sErrorLog));
    }

}
