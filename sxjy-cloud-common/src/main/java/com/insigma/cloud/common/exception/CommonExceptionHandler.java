package com.insigma.cloud.common.exception;

import com.insigma.cloud.common.context.SUserUtil;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.utils.IPUtils;
import com.insigma.cloud.rpc.LogRpcService;
import com.insigma.mvc.model.SErrorLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class CommonExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);


    @Autowired
    private LogRpcService logRpcService;
    /**
     * exception
     * @param e
     * @return
     */
    @ExceptionHandler(value=Exception.class)
    AjaxReturnMsg exception(Exception e, HttpServletRequest request) {
        logger.error(e.getMessage());
        //e.printStackTrace();
        saveErrorLog(e,request);
        return AjaxReturnMsg.error500();
    }

    /**
     * appexception
     * @param e
     * @return
     */
    @ExceptionHandler(value=AppException.class)
    AjaxReturnMsg appexception(AppException e, HttpServletRequest request) {
        logger.error(e.getMessage());
        e.printStackTrace();
        saveErrorLog(e,request);
        return AjaxReturnMsg.error500();
    }

    /**
     * 记录全局异常日志
     * @param e
     * @param request
     */
    public void saveErrorLog(Exception e, HttpServletRequest request){
        SErrorLog sErrorLog = new SErrorLog();
        if (e.getMessage() != null) {
            sErrorLog.setMessage(e.getMessage().length() > 500 ? e.getMessage().substring(0, 499) : e.getMessage());
        }
        sErrorLog.setStackmsg(getStackMsg(e));
        sErrorLog.setExceptiontype(e.getClass().getName());
        String ip = IPUtils.getIpAddr(request);
        sErrorLog.setIpaddr(ip);
        sErrorLog.setUsergent(request.getHeader("user-agent"));
        StringBuffer url = request.getRequestURL();
        if (request.getQueryString() != null && !("").equals(request.getQueryString())) {
            url.append("?" + request.getQueryString());
        }
        sErrorLog.setUrl(url.toString());
        String cookie = "";
        if (request.getCookies() != null) {
            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                Cookie tempcookie = cookies[i];
                cookie += tempcookie.getName() + ":" + tempcookie.getValue();
            }
            sErrorLog.setCookie(cookie.length() > 500 ? cookie.substring(0, 499) : cookie);
        }
        logRpcService.saveSErrLog(sErrorLog);
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
