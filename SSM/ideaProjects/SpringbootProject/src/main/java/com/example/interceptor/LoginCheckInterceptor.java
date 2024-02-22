package com.example.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.pojo.Result;
import com.example.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    //目标资源方法运行前执行，返回true表示放行，返回false表示不放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        //1，获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求路径url: {}", url);
        //2，判断请求url中是否包含login，如果包含说明是登录操作则放行
        if(url.contains("login")){
            log.info("登陆操作，放行！！！");
            return true;
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
            return false;
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
            return false;
        }
        //6,放行
        log.info("令牌合法，放行！！！");
        return true;
    }

    //目标资源方法运行后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){
        System.out.println("postHandle已执行。。。。。。。。。");
    }

    //视图渲染完毕后执行，最后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e){
        System.out.println("afterCompletion已执行。。。。。。。。。");
    }
}
