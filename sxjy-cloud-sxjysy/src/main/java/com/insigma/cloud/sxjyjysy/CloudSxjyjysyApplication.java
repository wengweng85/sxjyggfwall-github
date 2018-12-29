package com.insigma.cloud.sxjyjysy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients(basePackages = {"com.insigma.cloud.rpc"})
@EnableCaching
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.insigma.cloud.sxjyjysy")
@MapperScan(basePackages = {"com.insigma.cloud.*.dao"})
public class CloudSxjyjysyApplication /*extends SpringBootServletInitializer*/  {



	public static void main(String[] args) {
		SpringApplication.run(CloudSxjyjysyApplication.class, args);
	}

	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CloudSxjyjysyApplication.class);
	}*/
}
