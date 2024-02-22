package com.example.exception;

import com.example.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
* 全局异常处理器
* */
@RestControllerAdvice
public class GlobalExceptionHandler {

    //捕获所有异常
    @ExceptionHandler(Exception.class)
    public Result ex(Exception ex){
        ex.printStackTrace();
        return Result.error("抱歉！操作失败！详情请参考控制台输出！");
    }
}
