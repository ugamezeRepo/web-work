package com.example.boot06.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.example.boot06.interceptor.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // @Component 어노테이션을 통해 bean이 된 LoginInterceptor의 참조값 DI 받기
    @Autowired
    private LoginInterceptor loginInter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 동작을 원하는 인터셉터를 registry 객체를 이용해 등록
        registry.addInterceptor(loginInter).addPathPatterns("/sub/*", "/cafe/**")
                .excludePathPatterns("/sub/play", "/cafe/list", "/cafe/detail");
    }
}
