package com.insigma.cloud.base.service.common.svercord;

import java.util.List;

import com.insigma.mvc.model.Svercord;

public interface ApiSvercordService {
	
	int deleteByPrimaryKey(String vercodeid);

	int insert(Svercord record);

	int insertSelective(Svercord record);

	Svercord selectByPrimaryKey(String vercodeid);

	int updateByPrimaryKeySelective(Svercord record);

	int updateByPrimaryKey(Svercord record);

	//根据手机号码查询发送的验证码信息
	List<Svercord> querySvercordListByMobile(String mobile);

}
