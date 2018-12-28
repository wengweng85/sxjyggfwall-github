package com.insigma.cloud.base.dao.common.svercord;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.insigma.mvc.model.Svercord;

@Mapper
public interface ApiSvercordMapper {
    int deleteByPrimaryKey(String vercodeid);

    int insert(Svercord record);

    int insertSelective(Svercord record);

    Svercord selectByPrimaryKey(String vercodeid);

    int updateByPrimaryKeySelective(Svercord record);

    int updateByPrimaryKey(Svercord record);
    
    //根据手机号码查询发送的验证码信息
    List<Svercord> querySvercordListByMobile(@Param("mobile") String mobile);
}