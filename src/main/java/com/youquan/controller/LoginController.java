package com.youquan.controller;

import com.youquan.common.Result;
import com.youquan.pojo.Emp;
import com.youquan.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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
        Emp empDB = empService.login(emp.get("username"), emp.get("password"));
        return empDB != null ? Result.success() : Result.error("登录失败，用户名或密码错误！");
    }
}
