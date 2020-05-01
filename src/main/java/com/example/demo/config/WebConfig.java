package com.example.demo.config;

import com.example.demo.interceptor.LoginInterceptor;
import com.example.demo.interceptor.NotificationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private NotificationInterceptor notificationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/modes/**")
                .excludePathPatterns("/editor*/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/imgs/**")
                .excludePathPatterns("/favicon.ico");
        registry.addInterceptor(notificationInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/modes/**")
                .excludePathPatterns("/editor*/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/imgs/**")
                .excludePathPatterns("/favicon.ico");
    }
}
