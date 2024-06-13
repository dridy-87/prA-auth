package com.miris.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.miris.auth.interceptor.AuthInterceptor;

/**
 * 
 * @FileName : WebConfig.java

 * @작성자 : yg87.kim

 * @작성일 : 2024. 06. 13

 * @프로그램 설명 : 인터셉터 등록

 * @변경이력 :
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**") // 모든 경로에 대해 인터셉터를 적용합니다.
                .excludePathPatterns("/exclude/**"); // 특정 경로는 제외할 수 있습니다.
    }
}