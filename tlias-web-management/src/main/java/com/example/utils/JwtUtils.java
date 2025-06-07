package com.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.sql.Date;
import java.util.Map;

public class JwtUtils {

    private static final String JWT_SECRET = "JWT_SECRET"; //秘钥
    private static final long JWT_EXPIRATION = 12*60*60*1000; // 12小时


    /**
     * 生成JWT
     */
    public static String generateJWT(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .setClaims(claims)
                .compact();
    }

    /**
     * 解析JWT
     */
    public static Claims parseJWT(String token) throws Exception {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
