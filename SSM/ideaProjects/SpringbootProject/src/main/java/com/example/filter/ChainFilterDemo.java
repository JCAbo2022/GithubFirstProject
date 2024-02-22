package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//过滤器链的执行顺序优先级是按照过滤器类名（字符串）的自然排序
//@WebFilter(urlPatterns = "/*")
public class ChainFilterDemo implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("ChainDemo 拦截到请求！！！---------执行放行前的逻辑处理！！！");
        //放行
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("ChainDemo 拦截到请求！！！---------执行放行后的逻辑处理！！！");
    }
}
