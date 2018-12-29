package com.insigma.cloud.sxjyjysy.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.cloud.sxjyjysy.dao.ApiAc11Mapper;
import com.insigma.cloud.sxjyjysy.service.ApiAc11Service;
import com.insigma.mvc.model.Ac11;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wengsh on 2018/12/29.
 */
@Service
public class ApiAc11ServiceImpl implements ApiAc11Service {

    @Autowired
    private ApiAc11Mapper apiAc11Mapper;

    @Override
    public PageInfo<Ac11> getList(Ac11 ac11) {
        PageHelper.startPage(ac11.getCurpage(), ac11.getLimit());
        List<Ac11> list= apiAc11Mapper.getList();
        PageInfo<Ac11> pageinfo = new PageInfo<>(list);
        return pageinfo;

    }

    @Override
    public Ac11 getById(String id) {
        return apiAc11Mapper.getById(id);
    }
}
