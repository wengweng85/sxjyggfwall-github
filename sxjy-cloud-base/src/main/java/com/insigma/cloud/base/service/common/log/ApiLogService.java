package com.insigma.cloud.base.service.common.log;

import com.github.pagehelper.PageInfo;
import com.insigma.mvc.model.SAppLog;
import com.insigma.mvc.model.SErrorLog;
import com.insigma.mvc.model.SLog;
import com.insigma.mvc.model.SUserLog;
import org.springframework.stereotype.Repository;

/**
 * ��־����
 *
 * @author admin
 */
public interface ApiLogService {

    String saveSErrorLog(SErrorLog sErrorLog);

    PageInfo<SErrorLog>  getErrorLogList(SErrorLog sErrorLog);

    PageInfo<SErrorLog>  getErrorLogListByPage(SErrorLog sErrorLog);

    SErrorLog queryErrorLogById(String id);

    int deleteErrorLog(String id);

    String saveSLog(SLog sLog);

    String saveUserLog(SUserLog sUserLog);

    String saveAppLog(SAppLog sAppLog);

}
