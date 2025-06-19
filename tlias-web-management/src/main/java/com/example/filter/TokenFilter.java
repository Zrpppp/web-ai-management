package com.example.filter;

import com.example.utils.CurrentHolder;
import com.example.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.获取请求路径
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI();

        //2.判断是否是登录请求
        if ("/login".equals(path)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //3.获取token
        String token = request.getHeader("token");

        //4.判断token是否为空,不存在则返回401
        if (token == null || token.isEmpty()) {
           response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
           return;
        }

        //校验令牌
        try {
            Claims claims = JwtUtils.parseJWT(token);

            //获取员工id存入ThreadLocal
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
            log.info("当前员工id为：{}",empId);

        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //放行
        filterChain.doFilter(servletRequest, servletResponse);

        //删除ThreadLocal中的数据
        CurrentHolder.remove();
    }
}
