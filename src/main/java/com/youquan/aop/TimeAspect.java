package com.youquan.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Fengyouquan
 */
@Slf4j
@Component
@Aspect
public class TimeAspect {
    @Around("@annotation(com.youquan.anno.TimeAnnotation)")
    public Object recordTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        long end = System.currentTimeMillis();
        log.info(proceedingJoinPoint.getSignature() + "方法执行耗时：{}", (end - start));
        return result;
    }
}
