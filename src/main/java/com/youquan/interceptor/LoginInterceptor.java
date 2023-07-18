package com.youquan.interceptor;

import com.alibaba.fastjson2.JSONObject;
import com.youquan.common.Result;
import com.youquan.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    // 实现preHandle方法，该方法在请求处理之前被调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取Token
        String token = request.getHeader("Token");

        // 如果Token为空，返回错误信息
        if (!StringUtils.hasLength(token)) {
            // 如果 token 没有长度，创建一个错误结果对象
            Result<?> error = Result.error("NOT_LOGIN");

            // 将错误结果对象转换为 JSON 字符串
            String notLogin = JSONObject.toJSONString(error);

            // 设置响应的内容类型为 JSON,并使用 UTF-8 编码
            response.setContentType("application/json;charset=utf-8");

            // 将 JSON 字符串写入响应流中
            response.getWriter().write(notLogin);

            // 返回 false,表示请求处理失败
            return false;
        }


        // 尝试解析Token,如果解析失败，返回错误信息
        try {
            // 尝试使用JwtUtils的parseJWT方法解析token
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            // 如果解析过程中出现异常
            // 创建一个错误结果对象，其中包含错误信息"NOT_LOGIN"
            Result<?> error = Result.error("NOT_LOGIN");
            // 将错误结果对象转换为JSON字符串
            String notLogin = JSONObject.toJSONString(error);
            // 设置响应的内容类型为JSON,并指定字符集为UTF-8
            response.setContentType("application/json;charset=utf-8");
            // 将JSON字符串写入响应
            response.getWriter().write(notLogin);
            return false;
        }

        // 如果Token合法，放行请求并记录日志
        log.info("令牌合法，放行");
        return true;
    }
}