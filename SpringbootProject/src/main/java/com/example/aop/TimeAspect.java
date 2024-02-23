package com.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class TimeAspect {

    @Pointcut("execution(* com.example.service.*.*(..))")//将切入点抽取出来对切入点进行复用
    public void pt(){}

    @Around("pt()")//切入点表达式
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕前-通知已执行。。。。。。");
        //1,记录开始时间
        long begin = System.currentTimeMillis();
        //2，调用原始方法
        Object object = joinPoint.proceed();
        //3，记录结束时间，计算方法执行耗时
        long end = System.currentTimeMillis();
        log.info(joinPoint.getSignature() + "方法执行耗时:{}ms", end - begin);
        System.out.println("环绕后-通知已执行。。。。。。");
        return object;
    }

    @Before("pt()")
    public void before(){
        System.out.println("前置-通知已执行。。。。。。");
    }

    @After("pt()")
    public void after(){
        System.out.println("后置-通知已执行。。。。。。");
    }

    @AfterReturning("execution(* com.example.service.*.*(..))")
    public void afterReturning(){
        System.out.println("后置返回-通知已执行。。。。。。");
    }

    @AfterThrowing("execution(* com.example.service.*.*(..))")
    public void afterThrowing(){
        System.out.println("后置异常-通知已执行。。。。。。");
    }
}
