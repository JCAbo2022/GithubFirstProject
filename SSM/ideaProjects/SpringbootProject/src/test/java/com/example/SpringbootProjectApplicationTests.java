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
        ����JWT
     */
    @Test
    public void testJWT(){

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "zhoujianbo");
        String jwt = Jwts.builder()
                .setClaims(claims)//�����Զ������ݣ��غɣ�
                .signWith(SignatureAlgorithm.HS256, "zhoujianbo")//ָ��ǩ���㷨����ԿΪzhoujianbo
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 3600 * 100))//������Ч��
                .compact();
        System.out.println(jwt);
    }

    /*
        ����JWT
    */
    @Test
    public void testParseJWT(){
        Claims claims = Jwts.parser()
                .setSigningKey("zhoujianbo")//���ý�����Կ��ͬ����ʱʹ�õ���Կ��
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNzA4Mjc4MjMxLCJ1c2VybmFtZSI6Inpob3VqaWFuYm8ifQ.ri7JadkldH-5Vq6APYKRBUcT2vvUxBGFikx2Qre2xpw")
                .getBody();
        System.out.println(claims);
    }

}
