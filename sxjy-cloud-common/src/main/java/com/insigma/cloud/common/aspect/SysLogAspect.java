package com.insigma.cloud.common.aspect;

import com.insigma.cloud.common.context.SUserUtil;
import com.insigma.cloud.common.utils.HttpContextUtils;
import com.insigma.cloud.common.utils.IPUtils;
import com.insigma.cloud.common.utils.JSONUtils;
import com.insigma.cloud.rpc.LogRpcService;
import com.insigma.mvc.model.SLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * 系统运行日志
 */
@Aspect
@Component
public class SysLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(SysLogAspect.class);

    @Autowired
    private LogRpcService logRpcService;

    @Pointcut("execution( * com.insigma..controller.*.*(..))")//两个..代表所有子目录，最后括号里的两个..代表所有参数
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        logger.debug("请求地址 : " +request.getRequestURL().toString());
        logger.debug("HTTP METHOD : " + request.getMethod());
        // 获取真实的ip地址
        logger.info("IP : " + IPUtils.getIpAddr(request));
        logger.debug("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.debug("参数 : " + Arrays.toString(joinPoint.getArgs()));
    }
    @AfterReturning(returning = "ret", pointcut = "logPointCut()")// returning的值和doAfterReturning的参数名一致
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容(返回值太复杂时，打印的是物理存储空间的地址)
        logger.debug("返回值 : " + ret);
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();// ob 为方法的返回值
        logger.debug("耗时 : " + (System.currentTimeMillis() - startTime));
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - startTime;
        //异步保存日志
        saveLog(pjp, time);
        return ob;
    }


    /**
     * 保存日志
     * @param joinPoint
     * @param time
     * @throws InterruptedException
     */
    void saveLog(ProceedingJoinPoint joinPoint, long time) throws InterruptedException {
        logger.debug("保存运行日志");
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        SLog sLog = new SLog();
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sLog.setMessage(className + "." + methodName + "()");
        sLog.setMethod(className + "." + methodName + "()");
        // 请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = JSONUtils.beanToJson(args[0]).substring(0, 4999);
            sLog.setQueryparam(params);
        } catch (Exception e) {
            sLog.setQueryparam(Arrays.toString(joinPoint.getArgs()));
        }
        sLog.setUserid(SUserUtil.getUserId()!=null?SUserUtil.getUserId():"0000000");
        sLog.setUrl(request.getRequestURL().toString());
        // 设置IP地址
        sLog.setIpaddr (IPUtils.getIpAddr(request));
        sLog.setUserid(SUserUtil.getUserId() == null ? "000000" : SUserUtil.getUserId());
        sLog.setCost (new Long(time).toString());
        sLog.setUsergent(request.getHeader("user-agent"));
        // 系统当前时间
        Date date = new Date();
        sLog.setLogtime(date);
        // 保存日志
        logRpcService.saveSLog(sLog);
    }
}
