package com.youquan.aop;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.youquan.mapper.OperateLogMapper;
import com.youquan.pojo.OperateLog;
import com.youquan.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Fengyouquan
 */
@Slf4j
@RequiredArgsConstructor
@Component
@Aspect
public class OperateLogAspect {
    private final HttpServletRequest httpServletRequest;
    private final OperateLogMapper operateLogMapper;

    @Around("@annotation(com.youquan.anno.OperateLog)")
    public Object recordOperateLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 获取请求头中的 Token
        String jwt = httpServletRequest.getHeader("Token");
        // 解析 Token,获取其中的声明信息
        Map<String, Object> claims = JwtUtils.parseJWT(jwt);
        // 从声明信息中获取用户 ID
        Integer id = (Integer) claims.get("id");

        // 获取当前时间作为操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        // 获取目标方法所在的类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        // 获取目标方法的名称
        String methodName = proceedingJoinPoint.getSignature().getName();
        // 获取目标方法的参数列表
        Object[] args = proceedingJoinPoint.getArgs();
        // 将参数列表转换为 JSON 字符串
        String methodParams = JSONObject.toJSONString(args);
        // 记录开始时间
        long start = System.currentTimeMillis();
        // 执行目标方法
        Object result = proceedingJoinPoint.proceed();
        // 将目标方法的返回值转换为 JSON 字符串
        String returnValue = JSON.toJSONString(result);
        // 记录结束时间
        long end = System.currentTimeMillis();
        // 计算操作耗时
        long costTime = end - start;

        // 创建操作日志对象
        OperateLog operateLog = new OperateLog(null, id, operateTime, className, methodName, methodParams, returnValue, costTime);

        // 将操作日志保存到数据库中
        operateLogMapper.save(operateLog);

        // 返回目标方法的执行结果
        return result;
    }

}
