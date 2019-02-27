package com.insigma.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {"com.insigma.cloud.rpc"})
@SpringBootApplication(scanBasePackages = "com.insigma.cloud")
public class CloudJpaApplication /*extends SpringBootServletInitializer */{

	public static void main(String[] args) {
		SpringApplication.run(CloudJpaApplication.class, args);
	}

	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CloudAuthApplication.class);
	}*/
}
