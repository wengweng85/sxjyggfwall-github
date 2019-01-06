package com.insigma.cloud.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class WebLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution( * com.insigma..controller.*.*(..))")//����..����������Ŀ¼����������������..�������в���
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        logger.info("�����ַ : " +request.getRequestURL().toString());
        logger.info("HTTP METHOD : " + request.getMethod());
        // ��ȡ��ʵ��ip��ַ
        //logger.info("IP : " + IPAddressUtil.getClientIpAddress(request));
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("���� : " + Arrays.toString(joinPoint.getArgs()));
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
        logger.info("��ʱ : " + (System.currentTimeMillis() - startTime));
        return ob;
    }
}
