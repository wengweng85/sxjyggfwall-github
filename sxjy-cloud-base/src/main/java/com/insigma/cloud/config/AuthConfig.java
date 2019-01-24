package com.insigma.cloud.config;

import com.insigma.cloud.common.intercepter.AuthUnNecessaryIntercepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class AuthConfig extends WebMvcConfigurationSupport {
    @Bean
    public AuthUnNecessaryIntercepter authIntercepter() {
        return new AuthUnNecessaryIntercepter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(authIntercepter());
        // 排除配置
        addInterceptor.excludePathPatterns("/v2/api-docs");
        addInterceptor.excludePathPatterns("/swagger-resources/**");
        addInterceptor.excludePathPatterns("/configuration/**");
        addInterceptor.excludePathPatterns("/webjars/**");
        addInterceptor.excludePathPatterns("/swagger-ui.html");
        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
}
