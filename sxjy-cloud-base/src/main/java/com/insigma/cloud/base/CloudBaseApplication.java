package com.insigma.cloud.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableFeignClients(basePackages = {"com.insigma.cloud.rpc"})
@EnableCaching
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.insigma.cloud.base")
@MapperScan(basePackages = {"com.insigma.cloud.*.dao"})
public class CloudBaseApplication /*extends SpringBootServletInitializer*/  {



	public static void main(String[] args) {
		SpringApplication.run(CloudBaseApplication.class, args);
	}

	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CloudBaseApplication.class);
	}*/
}
