package com.example.controller;

import com.example.aop.MyAnnotation;
import com.example.pojo.Emp;
import com.example.pojo.Result;
import com.example.service.EmpService;
import com.example.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    @MyAnnotation
    public Result login(@RequestBody Emp emp){
        log.info("员工登录信息：{}", emp);
        Emp e = empService.login(emp);

        //登陆成功，生成令牌，下发令牌
        if(e != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("name", e.getName());
            claims.put("username", e.getUsername());

            String jwt = JwtUtils.generateJwt(claims);//jwt包含了当前登录的员工信息
            return Result.success(jwt);
        }

        //登陆失败，返回错误信息
        return Result.error("用户名或密码错误！");
    }
}
