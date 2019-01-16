package com.insigma.cloud.sxjysy.service;

import com.github.pagehelper.PageInfo;
import com.insigma.mvc.model.Ac11;

/**
 * Created by wengsh on 2018/12/29.
 */
public interface ApiAc11Service {
    public PageInfo<Ac11> getList(Ac11 ac11);
    public Ac11 getById(String id);
}
