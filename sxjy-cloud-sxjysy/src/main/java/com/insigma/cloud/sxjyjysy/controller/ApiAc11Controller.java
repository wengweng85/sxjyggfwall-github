package com.insigma.cloud.sxjyjysy.controller;

import com.insigma.cloud.common.annotation.UserLog;
import com.insigma.cloud.common.context.SUserUtil;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.exception.AppException;
import com.insigma.cloud.sxjyjysy.service.ApiAc11Service;
import com.insigma.mvc.model.Ac11;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;

/**
 * @author admin
 *
 */
@RestController
@Api(description = "ac11")
public class ApiAc11Controller  {

    private static final Logger logger = LoggerFactory.getLogger(ApiAc11Controller.class);

    @Resource
    private ApiAc11Service apiAc11Service;

    /**
     * 获取列表
     * @return
     * @throws AppException
     */
    @UserLog("获取ac11列表")
    @ApiOperation(value = "ac11", notes = "ac11", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/ac11s", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg list(@RequestBody Ac11 ac11) throws AppException {
        return AjaxReturnMsg.success(apiAc11Service.getList(ac11));
    }

    /**
     * 获取明细
     * @return
     * @throws AppException
     */
    @UserLog("获取ac11明细")
    @ApiOperation(value = "ac11", notes = "ac11", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/ac11", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg detail(@RequestBody Ac11 ac11) throws AppException {
        //throw new AppException("发生异常了");
        return AjaxReturnMsg.success(apiAc11Service.getById(ac11.getEec001()));
    }

}
