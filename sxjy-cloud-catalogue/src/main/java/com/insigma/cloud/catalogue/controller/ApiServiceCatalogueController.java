package com.insigma.cloud.catalogue.controller;

import com.insigma.cloud.catalogue.service.ApiServiceCatalogueService;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.mvc.model.catalogue.SearchCondition;
import com.insigma.mvc.model.catalogue.ServiceCatalogue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 服务目录服务
 */
@Api(description = "服务目录")
@RestController
@RequestMapping("/catalogue")
public class ApiServiceCatalogueController {

    @Autowired
    private ApiServiceCatalogueService serviceDirectoryService;

    @ApiOperation(value = "服务目录首页", notes = "服务目录首页", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/index", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg index() throws Exception {
        return AjaxReturnMsg.success(serviceDirectoryService.getIndex());
    }

    @ApiOperation(value = "个人服务大厅", notes = "个人服务大厅", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/perServiceHall",produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg perServiceHall(@RequestBody SearchCondition searchCondition) throws Exception {
        return AjaxReturnMsg.success(serviceDirectoryService.getPersonCataList(searchCondition));
    }

    @ApiOperation(value = "单位服务大厅", notes = "单位服务大厅", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/comServiceHall", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg comServiceHall(@RequestBody SearchCondition searchCondition) throws Exception {
        return AjaxReturnMsg.success(serviceDirectoryService.getComCataList(searchCondition));
    }


    @ApiOperation(value = "事项列表", notes = "事项列表", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value="/list" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg list(@RequestBody SearchCondition searchCondition) throws Exception {
        return AjaxReturnMsg.success(serviceDirectoryService.list(searchCondition));
    }

    @ApiOperation(value = "事项详情", notes = "事项详情", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg detail(@RequestBody ServiceCatalogue serviceCatalogue) throws Exception {
        return  AjaxReturnMsg.success(serviceDirectoryService.getById(serviceCatalogue.getCata_id()));
    }

}
