package com.insigma.cloud.sxjysy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.insigma.mvc.model.Ac11;

/**
 * Created by wengsh on 2018/12/29.
 */
public interface ApiAc11Service {
    public IPage<Ac11> getList(Ac11 ac11);
    public Ac11 getById(String id);
}
