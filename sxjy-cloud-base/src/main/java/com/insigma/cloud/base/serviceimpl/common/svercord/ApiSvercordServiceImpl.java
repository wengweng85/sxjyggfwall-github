package com.insigma.cloud.base.serviceimpl.common.svercord;

import com.insigma.cloud.base.dao.common.svercord.ApiSvercordMapper;
import com.insigma.cloud.base.service.common.svercord.ApiSvercordService;
import com.insigma.mvc.model.Svercord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiSvercordServiceImpl implements ApiSvercordService {
	
	@Resource
	private ApiSvercordMapper apiSvercordMapper;

	@Override
	public int deleteByPrimaryKey(String vercodeid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Svercord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Svercord record) {
		int insertSelective = apiSvercordMapper.insertSelective(record);
		return insertSelective;
	}

	@Override
	public Svercord selectByPrimaryKey(String vercodeid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Svercord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Svercord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	 
	 /**
	  * �����ֻ������ѯ���͵���֤����Ϣ
	  * @author hedd
	  */
	@Override
	public List<Svercord> querySvercordListByMobile(String mobile) {
		List<Svercord> list = new ArrayList<Svercord>();
		if(!"".equals(mobile) && mobile != null){
			list = apiSvercordMapper.querySvercordListByMobile(mobile);
		}
		return list;
	}

}
