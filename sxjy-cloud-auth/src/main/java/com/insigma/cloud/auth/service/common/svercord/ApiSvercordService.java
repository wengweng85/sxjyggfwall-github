package com.insigma.cloud.auth.service.common.svercord;

import com.insigma.mvc.model.Svercord;

import java.util.List;

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
