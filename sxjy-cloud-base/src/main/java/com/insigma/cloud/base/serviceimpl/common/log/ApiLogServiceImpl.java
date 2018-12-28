package com.insigma.cloud.base.serviceimpl.common.log;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.cloud.base.dao.common.log.ApiLogMapper;
import com.insigma.cloud.base.service.common.log.ApiLogService;
import com.insigma.cloud.common.utils.IPUtils;
import com.insigma.mvc.model.SErrorLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
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
    public String sysErrorLog(Exception e, HttpServletRequest request) {
        SErrorLog sysLog = new SErrorLog();
        if (e.getMessage() != null) {
            sysLog.setMessage(e.getMessage().length() > 500 ? e.getMessage().substring(0, 499) : e.getMessage());
        }
        sysLog.setStackmsg(getStackMsg(e));
        sysLog.setExceptiontype(e.getClass().getName());
        String ip = IPUtils.getIpAddr(request);
        /*IPSeekerUtil util=new IPSeekerUtil();*/
        sysLog.setIpaddr(ip);
        /*String country=util.getIpCountry(ip);
        sysLog.setIpaddr(country+"("+ip+")");*/
        sysLog.setUsergent(request.getHeader("user-agent"));
        StringBuffer url = request.getRequestURL();
        if (request.getQueryString() != null && !("").equals(request.getQueryString())) {
            url.append("?" + request.getQueryString());
        }
        sysLog.setUrl(url.toString());
        String cookie = "";
        if (request.getCookies() != null) {
            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                Cookie tempcookie = cookies[i];
                cookie += tempcookie.getName() + ":" + tempcookie.getValue();
            }
            sysLog.setCookie(cookie.length() > 500 ? cookie.substring(0, 499) : cookie);
        }
        logMapper.saveSysErrorLog(sysLog);
        return sysLog.getLogid();
    }

    @Override
    public PageInfo<SErrorLog> getErrorLogList( SErrorLog sErrorLog) {
        PageHelper.startPage(sErrorLog.getCurpage(), sErrorLog.getLimit());
        List<SErrorLog> list =logMapper.getErrorLogList();
        PageInfo<SErrorLog> pageinfo = new PageInfo<>(list);
        return pageinfo;
    }

    /**
     * 将异常打印出来
     *
     * @param e
     * @return
     */
    private static String getStackMsg(Exception e) {
        if (e != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return sw.toString();
        }
        return "";
    }
}