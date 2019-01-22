package com.insigma.cloud.common.aspect;

import com.insigma.cloud.common.annotation.UserLog;
import com.insigma.cloud.common.context.SUserUtil;
import com.insigma.cloud.common.utils.HttpContextUtils;
import com.insigma.cloud.rpc.LogRpcService;
import com.insigma.mvc.model.SUserLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 用户操作日志全局aop
 */
@Aspect
@Component
public class UserLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(UserLogAspect.class);

    @Autowired
    private LogRpcService logRpcService;

    @Pointcut("@annotation(com.insigma.cloud.common.annotation.UserLog)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //异步保存日志
        saveLog(point,new Date(beginTime));
        return result;
    }


    /**
     * 保存用户操作日志
     * @param joinPoint
     * @param beginTime
     * @throws InterruptedException
     */
    void saveLog(ProceedingJoinPoint joinPoint,Date beginTime) throws InterruptedException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SUserLog sUserLog = new SUserLog();
        UserLog userLog = method.getAnnotation(UserLog.class);
        if (sUserLog != null) {
            // 注解上的描述
            sUserLog.setMessage(userLog.value());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sUserLog.setOptype(className + "." + methodName + "()");
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 系统当前时间
        Date date = new Date();
        sUserLog.setAae011(SUserUtil.getUserId()!=null?SUserUtil.getUserId():"0000000");
        // 开始时间
        sUserLog.setLogstime(date);
        // 结束时间
        sUserLog.setLogetime(beginTime);
        // 保存系统日志
        logRpcService.saveSUserLog(sUserLog);
    }

}

