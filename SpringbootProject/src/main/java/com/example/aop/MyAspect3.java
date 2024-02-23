package com.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(3)//控制通知执行顺序，数字越小前置通知越先执行，后置通知越后执行
@Slf4j
@Component
@Aspect
public class MyAspect3 {

    @Before("execution(* com.example.service.*.*(..))")
    public void before(){
        log.info("before ...3");
    }

    @After("execution(* com.example.service.*.*(..))")
    public void after(){
        log.info("after ...3");
    }

}
