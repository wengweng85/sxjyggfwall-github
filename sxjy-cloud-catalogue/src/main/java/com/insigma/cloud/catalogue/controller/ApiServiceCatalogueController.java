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
 * ����Ŀ¼����
 */
@Api(description = "����Ŀ¼")
@RestController
@RequestMapping("/catalogue")
public class ApiServiceCatalogueController {

    @Autowired
    private ApiServiceCatalogueService serviceDirectoryService;

    @ApiOperation(value = "����Ŀ¼��ҳ", notes = "����Ŀ¼��ҳ", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/index", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg index() throws Exception {
        return AjaxReturnMsg.success(serviceDirectoryService.getIndex());
    }

    @ApiOperation(value = "���˷������", notes = "���˷������", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/perServiceHall",produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg perServiceHall(@RequestBody SearchCondition searchCondition) throws Exception {
        return AjaxReturnMsg.success(serviceDirectoryService.getPersonCataList(searchCondition));
    }

    @ApiOperation(value = "��λ�������", notes = "��λ�������", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/comServiceHall", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg comServiceHall(@RequestBody SearchCondition searchCondition) throws Exception {
        return AjaxReturnMsg.success(serviceDirectoryService.getComCataList(searchCondition));
    }


    @ApiOperation(value = "�����б�", notes = "�����б�", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value="/list" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg list(@RequestBody SearchCondition searchCondition) throws Exception {
        return AjaxReturnMsg.success(serviceDirectoryService.list(searchCondition));
    }

    @ApiOperation(value = "��������", notes = "��������", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg detail(@RequestBody ServiceCatalogue serviceCatalogue) throws Exception {
        return  AjaxReturnMsg.success(serviceDirectoryService.getById(serviceCatalogue.getCata_id()));
    }

}
