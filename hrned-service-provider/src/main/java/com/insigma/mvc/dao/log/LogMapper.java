package com.insigma.mvc.dao.log;

import com.insigma.mvc.model.Log;

/**
 * 日志服务
 */
public interface LogMapper {
	public void add(Log log);
	public void update(Log log);
}
