package com.insigma.cloud.base.dao.common.log;

import com.insigma.mvc.model.SErrorLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ÈÕÖ¾¼ÇÂ¼mapper
 *
 * @author xxx
 */
@Mapper
public interface ApiLogMapper {

    void saveSysErrorLog(SErrorLog sErrorLog);

    List<SErrorLog> getErrorLogList();

}
