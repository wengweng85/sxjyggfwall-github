package com.insigma.cloud.base.dao.demo;

import com.insigma.mvc.model.DemoAc01;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApiDemoMapper {
	
	List<DemoAc01> getDemoAc01List(DemoAc01 ac01);

    int deleteByPrimaryKey(String aac001);

    int insertSelective(DemoAc01 record);

    DemoAc01 selectByPrimaryKey(String aac001);

    DemoAc01 selectNameByPrimaryKey(String aac001);

    int updateByPrimaryKeySelective(DemoAc01 record);

    int updateByPrimaryKey(DemoAc01 record);

    int batDeleteData(String[] ids);
    
    int selectByAac002(DemoAc01 ac01);
    
    int updateDemoAc01DemBusUuid(DemoAc01 ac01);
}