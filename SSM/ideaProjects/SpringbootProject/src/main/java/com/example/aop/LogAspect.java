package com.example.aop;

import com.alibaba.fastjson.JSONObject;
import com.example.mapper.OperateLogMapper;
import com.example.pojo.OperateLog;
import com.example.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.logging.LogManager;

@Slf4j
@Aspect
@Component
public class LogAspect {
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.example.annotation.MyLog)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable{

        //操作人id ---> 当前登录员工id
        //获取请求头中的jwt令牌，解析令牌
//        String jwt = httpServletRequest.getHeader("token");
//        Claims claims = JwtUtils.parseJWT(jwt);
//        Integer userId = (Integer) claims.get("id");

        //操作时间
        LocalDateTime time = LocalDateTime.now();

        //操作类名
        String className = joinPoint.getTarget().getClass().getName();

        //操作方法名
        String methodName = joinPoint.getSignature().getName();

        //操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        //记录方法执行开始时间
        long begin = System.currentTimeMillis();

        //调用原始目标方法执行
        Object result = joinPoint.proceed();

        //记录方法执行结束时间
        long end = System.currentTimeMillis();

        //方法返回值
        String returnValue = JSONObject.toJSONString(result);

        //操作耗时
        long costTime = end - begin;

        //记录日志
        //OperateLog operateLog = new OperateLog(null, userId, time, className, methodName, methodParams, returnValue, costTime);
        OperateLog operateLog = new OperateLog(null, null, time, className, methodName, methodParams, returnValue, costTime);
        operateLogMapper.insert(operateLog);
        log.info("AOP记录操作日志：{}", operateLog);
        return result;
    }

}
