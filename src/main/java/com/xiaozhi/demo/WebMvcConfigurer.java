package com.xiaozhi.demo;

import com.xiaozhi.demo.servlet.CustomSpringInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Create by lizhihui on 2018/8/9
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器 拦截规则
        //多个拦截器时 以此添加 执行顺序按添加顺序
        registry.addInterceptor(getHandlerInterceptor()).addPathPatterns("/*");
    }

    @Bean
    public static HandlerInterceptor getHandlerInterceptor() {
        return new CustomSpringInterceptor();
    }
}