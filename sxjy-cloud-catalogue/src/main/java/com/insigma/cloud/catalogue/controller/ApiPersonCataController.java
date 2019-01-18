package com.insigma.cloud.catalogue.controller;

import com.insigma.cloud.catalogue.service.ApiServiceCatalogueService;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.mvc.model.catalogue.ServiceCollection;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * �����������
 */
@RestController
@RequestMapping("/person/catalogue")
@Api(description = "�����������")
public class ApiPersonCataController {

    @Resource
    private ApiServiceCatalogueService serviceCatalogueService;

    /**
     * �ղغ�ȡ���ղ�
     *
     * @return
     */
    @ApiOperation(value = "�ղغ�ȡ���ղ�", notes = "�ղغ�ȡ���ղ�", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/toggleCollect", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg toggleCollect(@RequestBody ServiceCollection collection) throws Exception {
        serviceCatalogueService.toggleCollect(collection);
        return AjaxReturnMsg.success("�����ɹ�");
    }

    /**
     * ���ղص������б���ҳ��
     *
     * @param collection
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "���ղص������б���ҳ��", notes = "���ղص������б���ҳ��")
    @PostMapping(value = "/getFavoriteList", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg getFavoriteList(@RequestBody ServiceCollection collection) throws Exception {
        return AjaxReturnMsg.success(serviceCatalogueService.getFavoriteList(collection));
    }
}
