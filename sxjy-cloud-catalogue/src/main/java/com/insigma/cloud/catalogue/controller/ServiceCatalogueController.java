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
 * ����Ŀ¼����
 */
@Api(description = "����Ŀ¼")
@RestController
@RequestMapping("/catalogue")
public class ServiceCatalogueController  {

    @Autowired
    private ServiceCatalogueService serviceDirectoryService;

    @ApiOperation(value = "����Ŀ¼��ҳ", notes = "����Ŀ¼��ҳ", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public AjaxReturnMsg index() throws Exception {
        return AjaxReturnMsg.success(serviceDirectoryService.getIndex());
    }

    @ApiOperation(value = "���˷������", notes = "���˷������", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/perServiceHall", method = RequestMethod.POST)
    public AjaxReturnMsg perServiceHall(@RequestBody SearchCondition searchCondition) throws Exception {
        return AjaxReturnMsg.success(serviceDirectoryService.getPersonCataList(searchCondition));
    }

    @ApiOperation(value = "��λ�������", notes = "��λ�������", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/comServiceHall", method = RequestMethod.POST)
    public AjaxReturnMsg comServiceHall(@RequestBody SearchCondition searchCondition) throws Exception {
        return AjaxReturnMsg.success(serviceDirectoryService.getComCataList(searchCondition));
    }


    @ApiOperation(value = "�����б�", notes = "�����б�", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value="/list" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg list(@RequestBody SearchCondition searchCondition) throws Exception {
        return AjaxReturnMsg.success(serviceDirectoryService.list(searchCondition));
    }

    @ApiOperation(value = "��������", notes = "��������", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public AjaxReturnMsg detail(@RequestBody ServiceCatalogue serviceCatalogue) throws Exception {
        return  AjaxReturnMsg.success(serviceDirectoryService.getById(serviceCatalogue.getCata_id(), serviceCatalogue.getUserId()));
    }


}
