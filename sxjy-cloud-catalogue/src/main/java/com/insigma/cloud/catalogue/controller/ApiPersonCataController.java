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
 * 个人事项服务
 */
@RestController
@RequestMapping("/person/catalogue")
@Api(description = "个人事项服务")
public class ApiPersonCataController {

    @Resource
    private ApiServiceCatalogueService serviceCatalogueService;

    /**
     * 收藏和取消收藏
     *
     * @return
     */
    @ApiOperation(value = "收藏和取消收藏", notes = "收藏和取消收藏", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/toggleCollect", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg toggleCollect(@RequestBody ServiceCollection collection) throws Exception {
        serviceCatalogueService.toggleCollect(collection);
        return AjaxReturnMsg.success("操作成功");
    }

    /**
     * 我收藏的事项列表（分页）
     *
     * @param collection
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "我收藏的事项列表（分页）", notes = "我收藏的事项列表（分页）")
    @PostMapping(value = "/getFavoriteList", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg getFavoriteList(@RequestBody ServiceCollection collection) throws Exception {
        return AjaxReturnMsg.success(serviceCatalogueService.getFavoriteList(collection));
    }
}
