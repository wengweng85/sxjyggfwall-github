package com.insigma.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {"com.insigma.cloud.rpc"})
@SpringBootApplication(scanBasePackages = "com.insigma.cloud")
@MapperScan(basePackages = {"com.insigma.cloud.*.dao"})
public class CloudAuthorizeApplication /*extends SpringBootServletInitializer */{

	public static void main(String[] args) {
		SpringApplication.run(CloudAuthorizeApplication.class, args);
	}

	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CloudAuthApplication.class);
	}*/
}
