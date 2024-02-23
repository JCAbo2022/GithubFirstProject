package com.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class PointcutDemo {

    /*
            * ：单个独立的任意符号，可以通配任意返回值、包名、类名、方法名、任意类型的一个参数，也可以通配包、类、方法名的一部分
            .. ：多个连续的任意符号，可以通配任意层级的包，或任意类型、任意个数的参数
            根据业务需要，可以使用 且（&&）、或（||）、非（!） 来组合比较复杂的切入点表达式
            例如：execution(* com.itheima.service.DeptService.list(..)) || execution(* com.itheima.service.DeptService.delete(..))
    */
    //@Pointcut("execution(public com.example.pojo.Result com.example.controller.LoginController.login(com.example.pojo.Emp))")
    //@Pointcut("execution(com.example.pojo.Result login(com.example.pojo.Emp))")//异常，访问修饰符和包名.类名可以省略（包名.类名不建议省略）
    //@Pointcut("execution(* login(..))")
    /*  基于注解的方式来匹配切入点方法。这种方式虽然多一步操作，我们需要自定义一个注解，但
        是相对来比较灵活。我们需要匹配哪个方法，就在方法上加上对应的注解就可以了
    * */
    @Pointcut("@annotation(com.example.aop.MyAnnotation)")
    public void pt(){}

    @Before("pt()")
    public void before(){
        System.out.println("MyAspect1 ...... before ......已执行！");
    }

}
