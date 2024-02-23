package com.example;

import com.example.controller.DeptController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.xml.parsers.SAXParser;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
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

    @Autowired
    private ApplicationContext applicationContext;
    /**
     * bean的获取
     */
    @Test
    public void testGetBean(){
        //根据name获取bean
        DeptController bean1 = (DeptController) applicationContext.getBean("deptController");
        System.out.println(bean1);

        //根据类型获取bean
        DeptController bean2 = (DeptController) applicationContext.getBean(DeptController.class);
        System.out.println(bean2);

        //根据name和类型获取bean
        DeptController bean3 = (DeptController) applicationContext.getBean("deptController", DeptController.class);
        System.out.println(bean3);

    }

    @Autowired
    private SAXReader saxReader;

    //第三方bean的管理
    @Test
    public void testThirdBean() throws Exception {
        //SAXReader saxReader = new SAXReader();

        Document document = saxReader.read(this.getClass().getClassLoader().getResource("1.xml"));
        Element rootElement = document.getRootElement();
        String name = rootElement.element("name").getText();
        String age = rootElement.element("age").getText();

        System.out.println(name + " : " + age);
    }


    @Test
    public void testGetBean2(){
        Object saxReader = applicationContext.getBean("reader");
        System.out.println(saxReader);
    }

}
