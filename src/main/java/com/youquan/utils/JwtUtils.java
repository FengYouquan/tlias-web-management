package com.youquan.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    // 签名密钥
    private static final String SIGN_KEY = "fengyouquan";
    // 有效时间
    private static final Long EXPIRE = 43200000L;

    /**
     * 生成JWT令牌
     *
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return String JWT令牌
     */
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .addClaims(claims)// 自定义信息（有效载荷）
                .signWith(SignatureAlgorithm.HS256, SIGN_KEY)// 签名算法（头部）
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))// 过期时间
                .compact();
    }

    /**
     * 解析JWT令牌
     *
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt) {
        return Jwts.parser()
                .setSigningKey(SIGN_KEY)// 指定签名密钥
                .parseClaimsJws(jwt)// 指定令牌Token
                .getBody();
    }
}
