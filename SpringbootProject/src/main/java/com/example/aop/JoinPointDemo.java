package com.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//切面类
@Slf4j
@Aspect
@Component
public class JoinPointDemo {

    @Pointcut("execution(* com.example.service.EmpService.*(..))")
    private void pt(){}

    @Before("pt()")
    public void before(JoinPoint joinPoint){
        log.info("JoinPointDemo ... before ...");
    }

    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("JoinPointDemo around before ...");

        //1. 获取 目标对象的类名 .
        String className = joinPoint.getTarget().getClass().getName();
        log.info("目标对象的类名:{}", className);

        //2. 获取 目标方法的方法名 .
        String methodName = joinPoint.getSignature().getName();
        log.info("目标方法的方法名: {}",methodName);

        //3. 获取 目标方法运行时传入的参数 .
        Object[] args = joinPoint.getArgs();
        log.info("目标方法运行时传入的参数: {}", Arrays.toString(args));

        //4. 放行 目标方法执行 .
        Object result = joinPoint.proceed();

        //5. 获取 目标方法运行的返回值 .
        log.info("目标方法运行的返回值: {}",result);

        log.info("JoinPointDemo around after ...");
        return result;
    }
}
