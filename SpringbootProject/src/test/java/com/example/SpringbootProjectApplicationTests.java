package com.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//@SpringBootTest
class SpringbootProjectApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testUUID(){
        for(int i = 0; i < 100; i ++){
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
        }
    }

    /*
        生成JWT
     */
    @Test
    public void testJWT(){

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "zhoujianbo");
        String jwt = Jwts.builder()
                .setClaims(claims)//设置自定义内容（载荷）
                .signWith(SignatureAlgorithm.HS256, "zhoujianbo")//指定签名算法和密钥为zhoujianbo
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 3600 * 100))//设置有效期
                .compact();
        System.out.println(jwt);
    }

    /*
        解析JWT
    */
    @Test
    public void testParseJWT(){
        Claims claims = Jwts.parser()
                .setSigningKey("zhoujianbo")//设置解析密钥（同生成时使用的密钥）
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNzA4Mjc4MjMxLCJ1c2VybmFtZSI6Inpob3VqaWFuYm8ifQ.ri7JadkldH-5Vq6APYKRBUcT2vvUxBGFikx2Qre2xpw")
                .getBody();
        System.out.println(claims);
    }

}
