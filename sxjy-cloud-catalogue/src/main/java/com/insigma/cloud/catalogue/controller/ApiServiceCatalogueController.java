package com.insigma.cloud.catalogue.controller;

import com.insigma.cloud.catalogue.service.ApiServiceCatalogueService;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.mvc.model.catalogue.SearchCondition;
import com.insigma.mvc.model.catalogue.ServiceCatalogue;
import com.insigma.mvc.model.catalogue.ServiceDesk;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 服务目录服务
 */
@Api(description = "服务目录服务")
@RestController
@RequestMapping("/catalogue")
public class ApiServiceCatalogueController {

    @Autowired
    private ApiServiceCatalogueService serviceDirectoryService;


    @ApiOperation(value = "服务目录", notes = "服务目录", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/index", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg index() throws Exception {
        return AjaxReturnMsg.success(serviceDirectoryService.getIndex());
    }

    /**
     * 个人服务大厅
     * @param searchCondition
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "个人服务大厅", notes = "个人服务大厅", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/perServiceHall",produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg perServiceHall(@RequestBody SearchCondition searchCondition) throws Exception {
        return AjaxReturnMsg.success(serviceDirectoryService.getPersonCataList(searchCondition));
    }

    /**
     * 单位服务大厅
     * @param searchCondition
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "单位服务大厅", notes = "单位服务大厅", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/comServiceHall", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg comServiceHall(@RequestBody SearchCondition searchCondition) throws Exception {
        return AjaxReturnMsg.success(serviceDirectoryService.getComCataList(searchCondition));
    }

    /**
     * 事项列表
     * @param searchCondition
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "事项列表", notes = "事项列表", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value="/list" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg list(@RequestBody SearchCondition searchCondition) throws Exception {
        return AjaxReturnMsg.success(serviceDirectoryService.list(searchCondition));
    }

    /**
     * 事项详情
     * @param serviceCatalogue
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "事项详情", notes = "事项详情", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg detail(@RequestBody ServiceCatalogue serviceCatalogue) throws Exception {
        return  AjaxReturnMsg.success(serviceDirectoryService.getById(serviceCatalogue.getCata_id()));
    }


    /**
     * 开始事项
     * @param serviceDesk
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "开始事项", notes = "开始事项", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/startCataDesk", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg startCataDesk(@RequestBody ServiceDesk serviceDesk) throws Exception {
        return AjaxReturnMsg.success(serviceDirectoryService.startCataDesk(serviceDesk));
    }

    /**
     * 更新事项办结状态
     * @param serviceDesk
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "更新事项办结状态", notes = "更新事项办结状态", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/updateCataDesk", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg updateCataDesk(@RequestBody ServiceDesk serviceDesk) throws Exception {
        int i=serviceDirectoryService.updateCataDesk(serviceDesk);
        if(i==1){
            return AjaxReturnMsg.success("更新成功");
        }else{
            return AjaxReturnMsg.fail("更新失败");
        }
    }

    @ApiOperation(value = "结束事项", notes = "结束事项", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/queryCataDeskList", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg queryCataDeskList(@RequestBody ServiceDesk serviceDesk) throws Exception {
        return AjaxReturnMsg.success(serviceDirectoryService.queryCataDeskList(serviceDesk));
    }




}
