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

	//�����ֻ������ѯ���͵���֤����Ϣ
	List<Svercord> querySvercordListByMobile(String mobile);

}
