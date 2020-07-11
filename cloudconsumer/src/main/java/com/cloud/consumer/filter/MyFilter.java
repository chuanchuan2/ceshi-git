package com.cloud.consumer.filter;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.MappingMatch;
import java.io.IOException;


/*1.@Component就是把这个类注入到IOC容器中
2.@WebFilter(urlPatterns = "/Blogs",filterName = "blosTest")说明这是一个web过滤器，它拦截的url为/Blogs，过滤器名字为blogsTest
下面贴出实现接口之后的三个重构方法：*/
/*@Component
@WebFilter(urlPatterns = "/security/securitysuccess",filterName = "myFilter")*/
public class MyFilter{
    /*@Override     implements Filter
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest= (HttpServletRequest) request;

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.printf("过滤器实现");
        chain.doFilter(httpServletRequest,httpServletResponse);
    }

    @Override
    public void destroy() {

    }*/
}
