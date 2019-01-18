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
 * ϵͳ������־
 */
@Aspect
@Component
public class SysLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(SysLogAspect.class);

    @Autowired
    private LogRpcService logRpcService;

    @Pointcut("execution( * com.insigma..controller.*.*(..))")//����..����������Ŀ¼����������������..�������в���
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        logger.debug("�����ַ : " +request.getRequestURL().toString());
        logger.debug("HTTP METHOD : " + request.getMethod());
        // ��ȡ��ʵ��ip��ַ
        logger.info("IP : " + IPUtils.getIpAddr(request));
        logger.debug("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.debug("���� : " + Arrays.toString(joinPoint.getArgs()));
    }
    @AfterReturning(returning = "ret", pointcut = "logPointCut()")// returning��ֵ��doAfterReturning�Ĳ�����һ��
    public void doAfterReturning(Object ret) throws Throwable {
        // ���������󣬷�������(����ֵ̫����ʱ����ӡ��������洢�ռ�ĵ�ַ)
        logger.debug("����ֵ : " + ret);
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();// ob Ϊ�����ķ���ֵ
        logger.debug("��ʱ : " + (System.currentTimeMillis() - startTime));
        // ִ��ʱ��(����)
        long time = System.currentTimeMillis() - startTime;
        //�첽������־
        saveLog(pjp, time);
        return ob;
    }


    /**
     * ������־
     * @param joinPoint
     * @param time
     * @throws InterruptedException
     */
    void saveLog(ProceedingJoinPoint joinPoint, long time) throws InterruptedException {
        logger.debug("����������־");
        // ��ȡrequest
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        SLog sLog = new SLog();
        // ����ķ�����
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sLog.setMessage(className + "." + methodName + "()");
        sLog.setMethod(className + "." + methodName + "()");
        // ����Ĳ���
        Object[] args = joinPoint.getArgs();
        try {
            String params = JSONUtils.beanToJson(args[0]).substring(0, 4999);
            sLog.setQueryparam(params);
        } catch (Exception e) {
            sLog.setQueryparam(Arrays.toString(joinPoint.getArgs()));
        }
        sLog.setUserid(SUserUtil.getUserId()!=null?SUserUtil.getUserId():"0000000");
        sLog.setUrl(request.getRequestURL().toString());
        // ����IP��ַ
        sLog.setIpaddr (IPUtils.getIpAddr(request));
        sLog.setUserid(SUserUtil.getUserId() == null ? "000000" : SUserUtil.getUserId());
        sLog.setCost (new Long(time).toString());
        sLog.setUsergent(request.getHeader("user-agent"));
        // ϵͳ��ǰʱ��
        Date date = new Date();
        sLog.setLogtime(date);
        // ������־
        logRpcService.saveSLog(sLog);
    }
}
