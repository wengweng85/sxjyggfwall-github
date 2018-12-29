package com.insigma.cloud.sxjyjysy.controller;

import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.exception.AppException;
import com.insigma.cloud.sxjyjysy.service.ApiAc11Service;
import com.insigma.mvc.model.Ac11;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 事项及对应附件记录表
 * @author admin
 *
 */
@RestController
@Api(description = "ac11")
@RequestMapping(value = "/api")
public class ApiAc11Controller  {

    @Resource
    private ApiAc11Service apiAc11Service;

    /**
     * 获取列表
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "ac11", notes = "ac11", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/ac11s")
    public AjaxReturnMsg list(@RequestBody Ac11 ac11) throws AppException {
        return AjaxReturnMsg.success(apiAc11Service.getList(ac11));
    }

    /**
     * 获取明细
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "ac11", notes = "ac11", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/ac11")
    public AjaxReturnMsg detail(@RequestBody Ac11 ac11) throws AppException {
        return AjaxReturnMsg.success(apiAc11Service.getById(ac11.getEec001()));
    }

}
