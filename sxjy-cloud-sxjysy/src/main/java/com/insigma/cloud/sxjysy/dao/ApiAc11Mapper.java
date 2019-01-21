package com.insigma.cloud.sxjysy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.mvc.model.Ac11;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 *
 * ac11 mapper
 * @author  admin
 */
@Mapper
public interface ApiAc11Mapper extends BaseMapper<Ac11> {

    List<Ac11> getList();
    Ac11 getById(String id);

}
