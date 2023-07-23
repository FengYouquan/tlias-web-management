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


    /**
     * 登录方法，接收一个包含用户名和密码的HashMap作为请求体
     *
     * @param emp 包含用户名和密码的HashMap
     * @return 如果登录成功，返回包含JWT令牌的成功结果；如果登录失败，返回错误信息
     */
    @PostMapping("/login")
    private Result<?> login(@RequestBody HashMap<String, String> emp) {
        // 记录日志，输出emp的信息
        log.info("登录校验，emp:{}", emp);

        // 通过empService的login方法验证用户名和密码，返回Emp对象
        Emp empDb = empService.login(emp.get("username"), emp.get("password"));

        if (empDb != null) {
            // 如果Emp对象不为空，说明验证通过

            // 创建一个HashMap用于存储自定义信息
            HashMap<String, Object> claims = new HashMap<>();

            // 将Emp对象中的id、username和name存入claims中
            claims.put("id", empDb.getId());
            claims.put("username", empDb.getUsername());
            claims.put("name", empDb.getName());

            // 使用JWT工具类生成身份令牌，并将其作为响应返回
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }

        // 如果Emp对象为空，说明验证失败，返回错误信息
        return Result.error("登录失败，用户名或密码错误！");
    }


    /**
     * 修改密码方法
     *
     * @param password           包含新密码信息的HashMap对象，键为"password"和"rePassword",分别表示旧密码和新密码
     * @param httpServletRequest HttpServletRequest对象，用于获取请求头中的Token信息
     * @return Result对象，表示操作结果
     */
    @PostMapping("/password")
    public Result<?> password(@RequestBody HashMap<String, String> password, HttpServletRequest httpServletRequest) {
        // 从请求头中获取Token信息
        String token = httpServletRequest.getHeader("Token");

        // 记录日志，输出修改密码的信息和Token
        log.info("修改密码：{},{}", password, token);

        // 调用empService的password方法进行密码修改操作
        empService.password(password, token);

        // 返回成功的Result对象
        return Result.success();
    }


    /**
     * 用于显示欢迎页面的用户信息
     *
     * @param httpServletRequest Request请求
     * @return 统一响应结果
     */
    @GetMapping("/index")
    public Result<String> index(HttpServletRequest httpServletRequest) {
        // 从httpServletRequest中获取名为"Token"的header值，并将其存储在变量token中
        String token = httpServletRequest.getHeader("Token");

        // 使用log.info记录一条日志，表示正在登录Tlias教学管理系统，并显示携带的Token值
        log.info("正在登录Tlias教学管理系统，携带Token值为：{}", token);

        // 使用JwtUtils工具类的parseJWT方法解析传入的token,得到一个Claims对象
        Claims claims = JwtUtils.parseJWT(token);

        // 从claims对象中获取名为"name"的属性值，并将其转换为字符串类型，然后存储在变量name中
        String name = (String) claims.get("name");

        // 返回一个Result对象，其中包含成功的结果以及从claims中获取的name值
        return Result.success(name);
    }


    /**
     * 根据Token值删除用户信息
     *
     * @param httpServletRequest Request请求
     * @return Result统一响应结果
     */
    @GetMapping("/signout")
    public Result<?> signOut(HttpServletRequest httpServletRequest) {
        // 从httpServletRequest中获取名为"Token"的请求头信息，并将其值赋给变量token
        String token = httpServletRequest.getHeader("Token");
        // 记录一条日志，表示用户正在注销，同时输出token的值
        log.info("用户注销：{}", token);
        // 使用JwtUtils工具类解析token,得到一个Claims对象
        Claims claims = JwtUtils.parseJWT(token);
        // 从Claims对象中获取名为"id"的属性值，并将其转换为Integer类型，然后赋给变量id
        Integer id = (Integer) claims.get("id");
        // 调用empService的signOut方法，传入id参数，执行员工注销操作
        empService.signOut(id);
        // 返回一个表示成功的Result对象
        return Result.success();
    }

}
