package com.insigma.mvc.service.log;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insigma.common.enums.JkType;
import com.insigma.common.struct.Data;
import com.insigma.mvc.dao.log.LogMapper;
import com.insigma.mvc.model.Log;

/**
 * @author wengsh
 *
 */
@Service
@Transactional
public class LogServiceImpl implements LogService {
	
	@Resource
	private LogMapper logMapper;

	/**
	 * 新增
	 */
	@Override
	public String add(Data data,String request, JkType jktype) {
		 Log log=new Log();
		 log.setJkType(jktype.getCode());
		 log.setJkkey(data.getHeader().getServiceReqId());
		 log.setJkSender(data.getHeader().getServiceCode());
		 log.setJkSendXml(request);
		 logMapper.add(log);
		 return log.getJkId();
	}

	/**
	 * 更新
	 */
	@Override
	public void update(Data data,String response,String jkId) {
		 Log log=new Log();
	     log.setJkId(jkId);
	     log.setJkResult(data.getHeader().getBusiStatus());
	     log.setJkReturnCode(data.getHeader().getCommStatus());
	     log.setJkReturnMsg(data.getHeader().getMsg());
	     log.setJkReturnXml(response);
		 logMapper.update(log);
	}
}