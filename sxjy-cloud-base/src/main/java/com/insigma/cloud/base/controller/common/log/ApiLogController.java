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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * �����Ӧ������¼��
 * @author admin
 *
 */
@RestController
@Api(description = "��־��¼")
@RequestMapping(value = "/api")
public class ApiLogController  {

    @Resource
    private ApiLogService apiLogService;

    /**
     * ��ȡ��������б�
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "��ȡ���д�����־", notes = "��ȡ���д�����־", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/errorlog/list")
    public AjaxReturnMsg list(@RequestBody SErrorLog sErrorLog) throws AppException {
        return AjaxReturnMsg.success(apiLogService.getErrorLogList(sErrorLog));
    }

}
