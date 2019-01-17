package com.insigma.cloud.catalogue.serviceimpl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.cloud.catalogue.dao.ApiServiceCatalogueMapper;
import com.insigma.cloud.catalogue.service.ApiServiceCatalogueService;
import com.insigma.cloud.common.context.SUserUtil;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SUser;
import com.insigma.mvc.model.catalogue.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ApiServiceCatalogueServiceImpl implements ApiServiceCatalogueService {

    @Resource
    private ApiServiceCatalogueMapper serviceCatalogueMapper;

    /**
     * getIndex
     * @return
     * @throws Exception
     */
    @Override
    public Map getIndex() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("busTypeList", serviceCatalogueMapper.getBusTypeList(null));
        map.put("hotCataList", serviceCatalogueMapper.getHotCataList());
        return map;
    }

    /**
     * getPersonCataList
     * @param searchCondition
     * @return
     * @throws Exception
     */
    @Override
    public Map getPersonCataList(SearchCondition searchCondition) throws Exception {
        //设置当前用户id
        searchCondition.setUserId(SUserUtil.getUserId());
        Map<String, Object> map = new HashMap<>();
        List<ServiceBusType> busTypeList =  serviceCatalogueMapper.getBusTypeList("1");
        List<ServiceDepartment> departmentList =  serviceCatalogueMapper.getDepartmentList();
        map.put("busTypeList", busTypeList);
        map.put("departmentList", departmentList);
        Map<String, Object> listMap = new LinkedHashMap<>();

        switch (searchCondition.getSearchType()) {
            case "1":
                if (StringUtils.isNotEmpty(searchCondition.getBusTypeId())) {
                    List<ServiceCatalogue> itemList = serviceCatalogueMapper.searchCatalogue(searchCondition);
                    listMap.put(searchCondition.getBusTypeId(), itemList);
                } else {
                    for (ServiceBusType busType: busTypeList) {
                        searchCondition.setBusTypeId(busType.getBus_type_id());
                        List<ServiceCatalogue> itemList = serviceCatalogueMapper.searchCatalogue(searchCondition);
                        listMap.put(searchCondition.getBusTypeId(), itemList);
                    }
                }
                break;
            case "2":
                if (StringUtils.isNotEmpty(searchCondition.getDepartmentId())) {
                    List<ServiceCatalogue> itemList = serviceCatalogueMapper.searchCatalogue(searchCondition);
                    listMap.put(searchCondition.getDepartmentId(), itemList);
                } else {
                    for (ServiceDepartment department: departmentList) {
                        searchCondition.setDepartmentId(department.getDepartment_id());
                        List<ServiceCatalogue> itemList = serviceCatalogueMapper.searchCatalogue(searchCondition);
                        listMap.put(searchCondition.getDepartmentId(), itemList);
                    }
                }
                break;
        }

        map.put("listMap", listMap);

        return map;
    }

    /**
     * getComCataList
     * @param searchCondition
     * @return
     * @throws Exception
     */
    @Override
    public Map getComCataList(SearchCondition searchCondition) throws Exception {
        //设置当前用户id
        searchCondition.setUserId(SUserUtil.getUserId());
        Map<String, Object> map = new HashMap<>();
        List<ServiceBusType> busTypeList =  serviceCatalogueMapper.getBusTypeList("2");
        List<ServiceDepartment> departmentList =  serviceCatalogueMapper.getDepartmentList();
        map.put("busTypeList", busTypeList);
        map.put("departmentList", departmentList);

        Map<String, Object> listMap = new LinkedHashMap<>();
        switch (searchCondition.getSearchType()) {
            case "1":
                if (StringUtils.isNotEmpty(searchCondition.getBusTypeId())) {
                    List<ServiceCatalogue> itemList = serviceCatalogueMapper.searchCatalogue(searchCondition);
                    listMap.put(searchCondition.getBusTypeId(), itemList);
                } else {
                    for (ServiceBusType busType: busTypeList) {
                        searchCondition.setBusTypeId(busType.getBus_type_id());
                        List<ServiceCatalogue> itemList = serviceCatalogueMapper.searchCatalogue(searchCondition);
                        listMap.put(searchCondition.getBusTypeId(), itemList);
                    }
                }
                break;
            case "2":
                if (StringUtils.isNotEmpty(searchCondition.getDepartmentId())) {
                    List<ServiceCatalogue> itemList = serviceCatalogueMapper.searchCatalogue(searchCondition);
                    listMap.put(searchCondition.getDepartmentId(), itemList);
                } else {
                    for (ServiceDepartment department: departmentList) {
                        searchCondition.setDepartmentId(department.getDepartment_id());
                        List<ServiceCatalogue> itemList = serviceCatalogueMapper.searchCatalogue(searchCondition);
                        listMap.put(searchCondition.getDepartmentId(), itemList);
                    }
                }
                break;
        }

        map.put("listMap", listMap);
        return map;
    }

    /**
     * getById
     * @param cataId
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public Map getById(String cataId) throws Exception {
        //设置当前用户id
        String userId=SUserUtil.getUserId();
        ServiceCatalogue catalogue = serviceCatalogueMapper.getById(cataId, userId);
        Map<String, Object> map = new HashMap<>();
        if (catalogue!=null) {
            // 设立依据
            if (StringUtils.isNotEmpty(catalogue.getCata_establish())) {
                catalogue.setCata_establish(catalogue.getCata_establish().replaceAll("\\n", "<br>"));
            }
            // 受理条件
            if (StringUtils.isNotEmpty(catalogue.getCata_hand_term())) {
                catalogue.setCata_hand_term(catalogue.getCata_hand_term().replaceAll("\\n", "<br>"));
            }
            // 申请材料
            if (StringUtils.isNotEmpty(catalogue.getCata_app_material())) {
                catalogue.setCata_app_material(catalogue.getCata_app_material().replaceAll("\\n", "<br>"));
            }
            List<ServiceCatalogueDetail> detailList = serviceCatalogueMapper.getListByCataId(cataId);

            for (ServiceCatalogueDetail detail : detailList) {
                List<ServiceCatalogueAttach> attachList = serviceCatalogueMapper.getListByDetailId(detail.getCata_detail_id());
                detail.setAttachList(attachList);
            }

            // 保存用户的浏览记录
            ServiceBrowse browse = new ServiceBrowse();
            browse.setCata_id(cataId);
            browse.setUserId(userId);
            serviceCatalogueMapper.saveBrowse(browse);

            map.put("catalogue", catalogue);
            map.put("detailList", detailList);
        }
        return map;
    }

    /**
     * list
     * @param searchCondition
     * @return
     * @throws Exception
     */
    @Override
    public Map list(SearchCondition searchCondition) throws Exception {
        searchCondition.setUserId(SUserUtil.getUserId());
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> list = new HashMap<>();
        List<ServiceBusType> busTypeList =  serviceCatalogueMapper.getBusTypeList(null);
        map.put("busTypeList", busTypeList);
        for (ServiceBusType busType: busTypeList) {
            searchCondition.setBusTypeId(busType.getBus_type_id());
            List<ServiceCatalogue> itemList = serviceCatalogueMapper.searchCatalogue(searchCondition);
            list.put(busType.getBus_type_id(), itemList);
        }
        map.put("list", list);
        return map;
    }

    /**
     * toggleCollect
     * @param collection
     * @throws Exception
     */
    @Override
    public void toggleCollect(ServiceCollection collection) throws Exception {
        collection.setUserId(SUserUtil.getUserId());
        ServiceCollection serviceCollection =  serviceCatalogueMapper.getCollectByCataIdAndUserId(collection);
        //未收藏
        if (null == serviceCollection) {
            serviceCatalogueMapper.saveCollect(collection);
        }
        //已收藏,取消收藏
        serviceCatalogueMapper.deleteCollect(collection.getCata_id());
    }

    /**
     * getFavoriteList
     * @param collection
     * @return
     * @throws Exception
     */
    @Override
    public PageInfo getFavoriteList(ServiceCollection collection) throws Exception {
        collection.setUserId(SUserUtil.getUserId());
        PageHelper.startPage(collection.getCurpage(), collection.getLimit());
        List<ServiceCollection> list =  serviceCatalogueMapper.getListByUserId(collection.getUserId());
        PageInfo<ServiceCollection> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


}
