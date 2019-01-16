package com.insigma.cloud.catalogue.controller;

import com.insigma.cloud.catalogue.service.ServiceCatalogueService;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.mvc.model.catalogue.SearchCondition;
import com.insigma.mvc.model.catalogue.ServiceCatalogue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务目录服务
 */
@Api(description = "服务目录")
@RestController
@RequestMapping("/catalogue")
public class ServiceCatalogueController  {

    @Autowired
    private ServiceCatalogueService serviceDirectoryService;

    @ApiOperation(value = "服务目录首页", notes = "服务目录首页", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public AjaxReturnMsg index() throws Exception {
        return AjaxReturnMsg.success(serviceDirectoryService.getIndex());
    }

    @ApiOperation(value = "个人服务大厅", notes = "个人服务大厅", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/perServiceHall", method = RequestMethod.POST)
    public AjaxReturnMsg perServiceHall(@RequestBody SearchCondition searchCondition) throws Exception {
        return AjaxReturnMsg.success(serviceDirectoryService.getPersonCataList(searchCondition));
    }

    @ApiOperation(value = "单位服务大厅", notes = "单位服务大厅", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/comServiceHall", method = RequestMethod.POST)
    public AjaxReturnMsg comServiceHall(@RequestBody SearchCondition searchCondition) throws Exception {
        return AjaxReturnMsg.success(serviceDirectoryService.getComCataList(searchCondition));
    }


    @ApiOperation(value = "事项列表", notes = "事项列表", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value="/list" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg list(@RequestBody SearchCondition searchCondition) throws Exception {
        return AjaxReturnMsg.success(serviceDirectoryService.list(searchCondition));
    }

    @ApiOperation(value = "事项详情", notes = "事项详情", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public AjaxReturnMsg detail(@RequestBody ServiceCatalogue serviceCatalogue) throws Exception {
        return  AjaxReturnMsg.success(serviceDirectoryService.getById(serviceCatalogue.getCata_id(), serviceCatalogue.getUserId()));
    }


}
