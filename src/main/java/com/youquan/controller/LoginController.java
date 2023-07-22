package com.youquan.controller;

import com.youquan.common.Result;
import com.youquan.pojo.Emp;
import com.youquan.service.EmpService;
import com.youquan.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author Fengyouquan
 */
@Slf4j
@RestController
public class LoginController {
    private final EmpService empService;

    public LoginController(EmpService empService) {
        this.empService = empService;
    }

    @PostMapping("/login")
    private Result<?> login(@RequestBody HashMap<String, String> emp) {
        log.info("登录校验,emp:{}", emp);
        Emp empDb = empService.login(emp.get("username"), emp.get("password"));

        if (empDb != null) {
            // 自定义信息
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("id", empDb.getId());
            claims.put("username", empDb.getUsername());
            claims.put("name", empDb.getName());

            // 使用JWT工具类，生成身份令牌
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }

        return Result.error("登录失败，用户名或密码错误！");
    }

    @PostMapping("/password")
    public Result<?> password(@RequestBody HashMap<String, String> password, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Token");
        log.info("修改密码：{},{}", password, token);
        empService.password(password, token);
        return Result.success();
    }

    @GetMapping("/index")
    public Result<String> index(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Token");
        log.info("正在登录Tlias教学管理系统,携带Token值为：{}", token);
        Claims claims = JwtUtils.parseJWT(token);
        String name = (String) claims.get("name");
        return Result.success(name);
    }

    @GetMapping("/signout")
    public Result<?> signOut(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Token");
        log.info("用户注销：{}", token);
        Claims claims = JwtUtils.parseJWT(token);
        Integer id = (Integer) claims.get("id");
        empService.signOut(id);
        return Result.success();
    }
}
