package com.insigma.cloud.common.aspect;

import com.insigma.cloud.common.annotation.RedisLock;
import com.insigma.cloud.common.exception.AppException;
import com.insigma.cloud.common.redis.RedisDistributionLock;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * 分布式锁aop
 */
@Aspect
@Component
public class RedisLockAspect {

    private static final Logger logger = LoggerFactory.getLogger(RedisLockAspect.class);

    @Resource
    private RedisDistributionLock redisDistributionLock;

    @Around("@annotation(com.insigma.cloud.common.annotation.RedisLock)")
    public Object processAround(ProceedingJoinPoint pjp) throws Throwable {
        //获取方法上的注解对象
        String methodName = pjp.getSignature().getName();
        Class<?> classTarget = pjp.getTarget().getClass();
        Class<?>[] par = ((MethodSignature) pjp.getSignature()).getParameterTypes();
        Method objMethod = classTarget.getMethod(methodName, par);
        RedisLock redisLockAnnoation = objMethod.getDeclaredAnnotation(RedisLock.class);
        //拼装分布式锁的key
        String[] keys = redisLockAnnoation.keys();
        Object[] args = pjp.getArgs();
        Object arg = args[0];
        StringBuilder temp = new StringBuilder();
        temp.append(redisLockAnnoation.keyPrefix());
        for (String key : keys) {
            String getMethod = "get" + StringUtils.capitalize(key);
            temp.append(MethodUtils.invokeExactMethod(arg, getMethod)).append("_");
        }
        String redisKey = StringUtils.removeEnd(temp.toString(), "_");

        //执行分布式锁的逻辑
        if (redisLockAnnoation.isSpin()) {
            //阻塞锁
            int lockRetryTime = 0;
            try {
                while (!redisDistributionLock.lock(redisKey, redisLockAnnoation.expireTime())) {
                    if (lockRetryTime++ > redisLockAnnoation.retryTimes()) {
                        logger.error("lock exception. key:{}, lockRetryTime:{}", redisKey, lockRetryTime);
                        throw new AppException("阻塞锁");
                    }
                    Thread.sleep(redisLockAnnoation.waitTime());
                }
                return pjp.proceed();
            } finally {
                redisDistributionLock.unlock(redisKey);
            }
        } else {
            //非阻塞锁
            try {
                if (!redisDistributionLock.lock(redisKey)) {
                    logger.error("lock exception. key:{}", redisKey);
                    throw new AppException("非阻塞锁");
                }
                return pjp.proceed();
            } finally {
                redisDistributionLock.unlock(redisKey);
            }
        }
    }
}
