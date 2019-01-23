package com.insigma.cloud.zuul;

import com.insigma.cloud.zuul.filter.AppkeyFilter;
import com.insigma.cloud.zuul.filter.RateLimitFilter;
import com.insigma.cloud.zuul.filter.SignatrueFilter;
import com.insigma.cloud.zuul.filter.JwtFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableHystrixDashboard
@EnableFeignClients(basePackages = {"com.insigma.cloud.rpc"})
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
@MapperScan(basePackages = {"com.insigma.cloud.*.dao"})
public class CloudZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudZuulApplication.class, args);
	}

	@Bean
	public RateLimitFilter rateLimitFilter(){
		return new RateLimitFilter();
	}

	@Bean
	public AppkeyFilter appkeyFilter(){
		return new AppkeyFilter();
	}

	@Bean
	public SignatrueFilter signatrueFilter(){
		return new SignatrueFilter();
	}

	@Bean
	public JwtFilter tokenFilter(){
		return new JwtFilter();
	}

	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true); // 允许cookies跨域
		config.addAllowedOrigin("*");// #允许向该服务器提交请求的URI，*表示全部允许，在SpringMVC中，如果设成*，会自动转成当前请求头中的Origin
		config.addAllowedHeader("*");// #允许访问的头信息,*表示全部
		config.setMaxAge(18000L);// 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
		config.addAllowedMethod("OPTIONS");// 允许提交请求的方法，*表示全部允许
		config.addAllowedMethod("HEAD");
		config.addAllowedMethod("GET");// 允许Get的请求方法
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		config.addAllowedMethod("PATCH");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

}
