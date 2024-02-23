package com.example.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.pojo.Result;
import com.example.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    //初始化方法和销毁方法已默认实现
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1，获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求路径url: {}", url);
        //2，判断请求url中是否包含login，如果包含说明是登录操作则放行
        if(url.contains("login")){
            log.info("登陆操作，放行！！！");
            filterChain.doFilter(request, response);
            return;
        }
        //否则不是登陆操作则进行以下操作
        //3，获取请求头中的令牌（token)
        String jwt = request.getHeader("token");
        //4，判断令牌是否存在，如果不存在则返回错误信息
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，返回未登陆的信息！！！");
            Result error = Result.error("NOT_LOGIN!");
            //手动将error对象转换成json类型数据后设置到response中
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return;
        }
        //5,令牌存在则执行解析token，如果解析失败则返回未登录的错误结果
        try{
            JwtUtils.parseJWT(jwt);
        } catch (Exception e){
            log.info("令牌解析错误，返回未登陆的信息！！！");
            Result error = Result.error("NOT_LOGIN!");
            //手动将error对象转换成json类型数据后设置到response中
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return;
        }
        //6,放行
        log.info("令牌合法，放行！！！");
        filterChain.doFilter(request, response);
    }
}
