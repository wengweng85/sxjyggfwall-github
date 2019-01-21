package com.insigma.cloud.base.dao.common.log;

import com.insigma.mvc.model.SAppLog;
import com.insigma.mvc.model.SErrorLog;
import com.insigma.mvc.model.SLog;
import com.insigma.mvc.model.SUserLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ÈÕÖ¾¼ÇÂ¼mapper
 *
 * @author  admin
 */
@Mapper
public interface ApiLogMapper {

    void saveSErrorLog(SErrorLog sErrorLog);

    List<SErrorLog> getErrorLogList();

    void saveSLog(SLog sLog);

    void saveUserLog(SUserLog sUserLog);

    void saveAppLog(SAppLog sAppLog);

}
