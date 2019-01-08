package com.insigma.cloud.base.serviceimpl.common.log;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.cloud.base.dao.common.log.ApiLogMapper;
import com.insigma.cloud.base.service.common.log.ApiLogService;
import com.insigma.mvc.model.SErrorLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author admin
 */

@Service
public class ApiLogServiceImpl implements ApiLogService {

    @Resource
    private ApiLogMapper logMapper;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:SSS");

    /**
     * 应用异常日志
     */
    @Override
    public String addSysErrorLog(SErrorLog sErrorLog)  {
        logMapper.saveSysErrorLog(sErrorLog);
        return sErrorLog.getLogid();
    }

    @Override
    public PageInfo<SErrorLog> getErrorLogList( SErrorLog sErrorLog) {
        PageHelper.startPage(sErrorLog.getCurpage(), sErrorLog.getLimit());
        List<SErrorLog> list =logMapper.getErrorLogList();
        PageInfo<SErrorLog> pageinfo = new PageInfo<>(list);
        return pageinfo;
    }


}