package com.youquan;

import com.mysql.cj.log.Log;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// @SpringBootTest
class TliasWebManagementApplicationTests {

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
}
