package com.insigma.cloud.base.serviceimpl.common.log;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.cloud.base.dao.common.log.ApiLogMapper;
import com.insigma.cloud.base.service.common.log.ApiLogService;
import com.insigma.mvc.model.SErrorLog;
import com.insigma.mvc.model.SLog;
import com.insigma.mvc.model.SUserLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author admin
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ApiLogServiceImpl implements ApiLogService {

    @Resource
    private ApiLogMapper logMapper;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:SSS");

    /**
     * 应用异常日志
     */
    @Override
    public String saveSErrorLog(SErrorLog sErrorLog)  {
        logMapper.saveSErrorLog(sErrorLog);
        return sErrorLog.getLogid();
    }

    @Override
    public PageInfo<SErrorLog> getErrorLogList( SErrorLog sErrorLog) {
        PageHelper.startPage(sErrorLog.getCurpage(), sErrorLog.getLimit());
        List<SErrorLog> list =logMapper.getErrorLogList();
        PageInfo<SErrorLog> pageinfo = new PageInfo<>(list);
        return pageinfo;
    }


    /**
     * 运行日志
     */
    @Override
    public String saveSLog(SLog sLog)  {
        logMapper.saveSLog(sLog);
        return sLog.getLogid();
    }


    /**
     * 用户日志
     */
    @Override
    public String saveUserLog(SUserLog sUserLog)  {
        logMapper.saveUserLog(sUserLog);
        return sUserLog.getLogid();
    }

}