package com.insigma.cloud.catalogue.service;

import com.github.pagehelper.PageInfo;
import com.insigma.mvc.model.catalogue.SearchCondition;
import com.insigma.mvc.model.catalogue.ServiceCollection;
import com.insigma.mvc.model.catalogue.ServiceDesk;

import java.util.List;
import java.util.Map;

public interface ApiServiceCatalogueService {

    Map getIndex() throws Exception;

    Map getPersonCataList(SearchCondition searchCondition) throws Exception;

    Map getComCataList(SearchCondition searchCondition) throws Exception;

    Map getById( String userId) throws Exception;

    Map list(SearchCondition searchCondition) throws Exception;

    void toggleCollect(ServiceCollection collection) throws Exception;

    PageInfo getFavoriteList(ServiceCollection collection) throws Exception;

    String startCataDesk(ServiceDesk serviceDesk);

    int updateCataDesk(ServiceDesk serviceDesk);

    List<ServiceDesk> queryCataDeskList(ServiceDesk serviceDesk);
}
