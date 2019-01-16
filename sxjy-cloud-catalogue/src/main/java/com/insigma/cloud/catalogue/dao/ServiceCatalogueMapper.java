package com.insigma.cloud.catalogue.dao;

import com.insigma.mvc.model.catalogue.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ServiceCatalogueMapper {

    List<ServiceBusType> getBusTypeList(@Param("type") String type);

    List<ServiceDepartment> getDepartmentList();

    List<ServiceCatalogue> searchCatalogue(SearchCondition searchCondition);

    ServiceCatalogue getById(@Param("cataId") String cataId, @Param("userId") String userId);

    List<ServiceBusType> getCatalogueList();

    List<ServiceCatalogueDetail> getListByCataId(String cataId);

    List<ServiceCatalogueAttach> getListByDetailId(String detailId);

    ServiceCollection getCollectByCataIdAndUserId(ServiceCollection collection);

    void saveCollect(ServiceCollection collection) throws Exception;

    void saveBrowse(ServiceBrowse browse) throws Exception;

    void deleteCollect(String cataId) throws Exception;

    List<ServiceCollection> getListByUserId(String userId);

    List<ServiceCatalogue> getHotCataList();
}
