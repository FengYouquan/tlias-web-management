package com.youquan.controller;

import com.youquan.common.Result;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

/**
 * @author Fengyouquan
 */
@Slf4j
@RestController
public class SessionController {
    // 设置Cookie
    @GetMapping("/c1")
    public Result<?> cookie1(HttpServletResponse httpServletResponse) {
        httpServletResponse.addCookie(new Cookie("login_username", "fengyouquan"));
        return Result.success();
    }

    // 获取Cookie
    @GetMapping("/c2")
    public Result<?> cookie2(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        Stream.of(cookies).filter(cookie -> cookie.getName().equals("login_username")).forEach(cookie -> System.out.println(cookie.getValue()));
        return Result.success();
    }

    // 设置Session
    @GetMapping("/s1")
    public Result<?> session(HttpSession httpSession) {
        log.info("HttpSession-s1:{}", httpSession.hashCode());

        httpSession.setAttribute("loginUser", "Tom");
        return Result.success();
    }

    @GetMapping("/s2")
    public Result<?> session2(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        log.info("HttpSession-s2:{}", httpSession.hashCode());

        Object loginUser = httpSession.getAttribute("loginUser");
        log.info("loginUser:{}", loginUser);
        return Result.success(loginUser);
    }
}
