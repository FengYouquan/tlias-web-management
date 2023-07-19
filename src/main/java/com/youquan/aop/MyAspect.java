package com.youquan.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

/**
 * @author Fengyouquan
 */
@Slf4j
// @Component
// @Aspect
public class MyAspect {

    @Pointcut("execution(* com.youquan.service.DeptService.removeById(java.lang.Integer))")
    private void pt() {
    }

    @Before("pt()")
    public void before(JoinPoint joinPoint) {
        // 输出日志信息，表示切面类MyAspect的before方法正在执行
        log.info("切面类MyAspect的before方法正在执行...");
        // 输出日志信息，表示切面类MyAspect的before方法正在执行
        log.info(joinPoint.getSignature().getName(), "方法正在执行");
    }

    @Pointcut("@annotation(com.youquan.anno.MyAnnotation)")
    private void pt2() {
    }


    @After("pt2()")
    public void after(JoinPoint joinPoint) {
        log.info("切面类MyAspect的before方法正在执行...");
        log.info(joinPoint.getSignature().getName(), "方法正在执行");
    }

    /**
     * 使用@Around注解定义一个环绕通知(Around Advice),用于在目标方法执行前后进行一些操作。
     *
     * @param proceedingJoinPoint 表示目标方法的连接点对象，包含了目标方法的信息。
     * @return 返回目标方法的执行结果。
     */
    @Around("@annotation(com.youquan.anno.MyAnnotation)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // 获取目标方法所在类的类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        log.info("目标类的类名为：{}", className);

        // 获取目标方法的方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        log.info("目标方法的方法名为：{}", methodName);

        // 获取目标方法的参数列表
        Object[] args = proceedingJoinPoint.getArgs();
        log.info("目标方法传入的参数为：{}", Arrays.toString(args));

        // 执行目标方法，并获取其返回值
        Object result = proceedingJoinPoint.proceed();
        log.info("目标方法的返回值为：{}", result);

        return result;
    }
}
