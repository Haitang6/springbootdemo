package com.example.demo.interceptor;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private UserService userService;

    public LoginInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //通过cookie获取登录态
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
        } else {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("token".equals(name)) {
                    String tokenValue = cookie.getValue();
                    //如果根据tokenValue能查询到用户，就登录此用户
                    User user = userService.findByToken(tokenValue);
                    if (user == null) {
                        //请到首页进行登录
                    } else {
                        //把此用户放到session域
                        request.getSession().setAttribute("user", user);
                        break;
                    }
                }
            }
        }
        return true;
    }

}
