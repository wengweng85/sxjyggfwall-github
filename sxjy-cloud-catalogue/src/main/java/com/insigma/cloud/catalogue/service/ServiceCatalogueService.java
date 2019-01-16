package com.insigma.cloud.catalogue.service;

import com.github.pagehelper.PageInfo;
import com.insigma.mvc.model.catalogue.SearchCondition;
import com.insigma.mvc.model.catalogue.ServiceCollection;

import java.util.Map;

public interface ServiceCatalogueService {

    Map getIndex() throws Exception;

    Map getPersonCataList(SearchCondition searchCondition) throws Exception;

    Map getComCataList(SearchCondition searchCondition) throws Exception;

    Map getById(String cataId, String userId) throws Exception;

    Map list(SearchCondition searchCondition) throws Exception;

    void toggleCollect(ServiceCollection collection) throws Exception;

    PageInfo getFavoriteList(ServiceCollection collection) throws Exception;
}
