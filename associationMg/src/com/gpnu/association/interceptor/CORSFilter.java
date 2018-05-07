package com.gpnu.association.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter {

	//web.xml配置全局跨域请求   拦截器
	
	@Override
    public void init(FilterConfig var1) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
     HttpServletResponse response = (HttpServletResponse) servletResponse;
     HttpServletRequest request = (HttpServletRequest) servletRequest;
     response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
     response.setHeader("Access-Control-Allow-Credentials", "true");
     response.setHeader("P3P", "CP=CAO PSA OUR");
     if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
         response.addHeader("Access-Control-Allow-Methods", "POST,GET,TRACE,OPTIONS");
         response.addHeader("Access-Control-Allow-Headers", "Content-Type,Origin,Accept");
         response.addHeader("Access-Control-Max-Age", "120");
     }
       // response.addHeader("Access-Control-Allow-Origin", "*");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {}
	
}
