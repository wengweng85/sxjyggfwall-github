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

	//�����ֻ������ѯ���͵���֤����Ϣ
	List<Svercord> querySvercordListByMobile(String mobile);

}
