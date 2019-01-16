package com.insigma.cloud.config;

import com.insigma.cloud.common.intercepter.AuthUnNecessaryIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class AuthConfig extends WebMvcConfigurationSupport {
    //@Bean
    public AuthUnNecessaryIntercepter authIntercepter() {
        return new AuthUnNecessaryIntercepter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(authIntercepter());
        // 排除配置
        addInterceptor.excludePathPatterns("/**");
    }
}
