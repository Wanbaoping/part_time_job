package com.baoge.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截/admin所有路径
        // 注册自定义两个拦截器
        registry.addInterceptor(new AdminLoginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin/loginAct")
                .excludePathPatterns("/admin/getCode");
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/dashboard/**");
    }
}
