package com.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    /**
     * 生成JWT
     */
    @Test
    public void testGenerateJWT() {

        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("id", 1);
        dataMap.put("username", "admin");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "secret")
                .addClaims(dataMap)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();

        System.out.println(jwt);
    }

    /**
     * 解析JWT
     */
    @Test
    public void testParseJWT() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc0OTI5MDgwMn0.2Z-ZF8omXZOGbqhWrmTZe0F0XwtiLTbEu6mSQrYCdB0";
        Claims secret = Jwts.parser()
                .setSigningKey("secret")
                .parseClaimsJws(token)
                .getBody();
        System.out.println(secret);
    }
}
