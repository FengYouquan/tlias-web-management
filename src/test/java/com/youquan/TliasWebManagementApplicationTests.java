package com.youquan;

import com.youquan.controller.DeptController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootTest
class TliasWebManagementApplicationTests {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
    }

    @Test
    public void testGenJwt() {
        // 创建一个新的HashMap对象来存储JWT的声明部分(claims)
        HashMap<String, Object> claims = new HashMap<>();
        // 将键为"id"的声明放入claims中，其值为1
        claims.put("id", 1);
        // 将键为"username"的声明放入claims中，其值为"Tom"
        claims.put("username", "Tom");

        String jwt = Jwts.builder()
                // 在JWT中设置claims部分
                .setClaims(claims)
                // 使用HS256算法和密钥"fengyouquan"对JWT进行签名
                .signWith(SignatureAlgorithm.HS256, "fengyouquan")
                // 为JWT设置过期时间，这里设置的是当前时间加上3分钟
                .setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 1000))
                // 将构建好的JWT转为字符串形式并返回
                .compact();

        // 将生成的JWT打印到控制台
        System.out.println(jwt);
    }

    @Test
    public void testParseJwt() {
        // 创建一个Map对象，用于存储JWT中的声明信息
        Map<String, Object> claims = Jwts.parser()
                // 设置签名密钥为"fengyouquan"
                .setSigningKey("fengyouquan")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNjg5NjQ4NTIwLCJ1c2VybmFtZSI6IlRvbSJ9.9fnxmUziHn4ppxZeAtpERentkOiH2zRdYi_LLOERp_Y")
                // 解析JWT并获取其中的声明部分
                .getBody();

        // 将声明信息打印到控制台
        System.out.println(claims);
    }

    @Test
    public void testGetBean() {
        // 根据name获取Bean
        DeptController deptController = (DeptController) applicationContext.getBean("deptController");
        log.info("获取Bean对象方式一：{}", deptController);

        // 根据bean的类型获取
        DeptController deptController2 = applicationContext.getBean(DeptController.class);
        log.info("获取Bean对象方式二：{}", deptController2);

        // 根据bean的名称 及 类型获取
        DeptController deptController3 = applicationContext.getBean("deptController", DeptController.class);
        log.info("获取Bean对象方式三：{}", deptController3);
    }


    @Autowired
    private SAXReader saxReader;

    @Test
    public void testThirdBean() throws DocumentException {
        Document document = saxReader.read(this.getClass().getClassLoader().getResource("1.xml"));
        Element rootElement = document.getRootElement();
        String name = rootElement.element("name").getText();
        String age = rootElement.element("age").getText();

        log.info(name + "：" + age);
    }
}
