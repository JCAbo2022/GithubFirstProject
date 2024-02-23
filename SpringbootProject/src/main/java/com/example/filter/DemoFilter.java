package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//定义一个类，实现一个标准的Filter过滤器的接口
//@WebFilter(urlPatterns = "/login")
public class DemoFilter implements Filter {
    @Override//初始化方法，只调用一次
    public void init(FilterConfig filterConfig) throws ServletException{
        System.out.println("init初始化方法已执行！！！");
    }

    @Override//拦截请求后调用，调用多次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("FilterDemo 拦截到请求！！！---------执行放行前的逻辑处理！！！");
        //放行
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("FilterDemo 拦截到请求！！！---------执行放行后的逻辑处理！！！");
    }

    @Override//销毁方法，只调用一次
    public void destroy() {
        System.out.println("destroy销毁方法已执行！！！");
    }
}