package com.insigma.cloud.sxjysy.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.cloud.sxjysy.dao.ApiAc11Mapper;
import com.insigma.cloud.sxjysy.service.ApiAc11Service;
import com.insigma.mvc.model.Ac11;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wengsh on 2018/12/29.
 */
@Service
@Transactional
public class ApiAc11ServiceImpl implements ApiAc11Service {

    @Autowired
    private ApiAc11Mapper apiAc11Mapper;

    @Override
    public IPage<Ac11> getList(Ac11 ac11) {
        Page<Ac11> page =new Page(ac11.getCurpage(),ac11.getLimit());
        //PageHelper.startPage(ac11.getCurpage(), ac11.getLimit());
        IPage<Ac11> iPage= apiAc11Mapper.selectPage(page,null);
        //PageInfo<Ac11> pageinfo = new PageInfo<>(list);
        return iPage;

    }

    @Override
    public Ac11 getById(String eec001) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("eec001",eec001);
        return apiAc11Mapper.selectOne(queryWrapper);
    }
}
