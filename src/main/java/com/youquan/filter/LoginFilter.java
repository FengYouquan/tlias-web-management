package com.youquan.filter;

import com.alibaba.fastjson2.JSONObject;
import com.youquan.common.Result;
import com.youquan.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@Slf4j
// @WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        // 获取请求的URL
        String url = httpServletRequest.getRequestURL().toString();
        // 如果URL包含"/login",则表示该请求不需要身份验证，直接放行
        if (url.contains("/login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 从请求头中获取"Token"字段，用于后续的身份验证
        String token = httpServletRequest.getHeader("Token");

        // 如果Token为空，表示用户未登录，返回错误信息
        if (!StringUtils.hasLength(token)) {
            Result<?> error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().write(notLogin);
            return;
        }

        // 尝试解析Token,如果解析失败，表示Token无效，返回错误信息
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            Result<?> error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().write(notLogin);
            return;
        }

        // 如果Token有效，记录日志并放行请求
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

