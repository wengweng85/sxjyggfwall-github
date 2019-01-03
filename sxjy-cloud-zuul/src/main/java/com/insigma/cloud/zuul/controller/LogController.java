package com.insigma.cloud.zuul.controller;

import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.zuul.rpc.admin.LogService;
import com.insigma.mvc.model.SErrorLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @version V1.0
 */
@RestController
public class LogController {
    @Autowired
    LogService logService;

    @GetMapping({"/test"})
    AjaxReturnMsg test(HttpServletRequest request)  {
        //SUserUtil.setToken(request.getHeader(CommonConstants.CONTEXT_TOKEN));
        SErrorLog sErrorLog=new SErrorLog();
        sErrorLog.setCurpage(1);
        sErrorLog.setLimit(10);
        return logService.all(sErrorLog);
    }
}
