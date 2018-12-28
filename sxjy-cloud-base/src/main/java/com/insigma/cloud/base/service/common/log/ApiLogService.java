package com.insigma.cloud.base.service.common.log;

import com.github.pagehelper.PageInfo;
import com.insigma.mvc.model.SErrorLog;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志服务
 *
 * @author admin
 */
@Repository
public interface ApiLogService {

    String sysErrorLog(Exception e, HttpServletRequest request);
    PageInfo<SErrorLog>  getErrorLogList(SErrorLog sErrorLog);

}
