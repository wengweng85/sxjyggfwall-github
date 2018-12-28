package com.insigma.cloud.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.insigma.cloud.*.dao"})
@SpringBootApplication
public class CloudBaseApplication /*extends SpringBootServletInitializer*/  {



	public static void main(String[] args) {
		SpringApplication.run(CloudBaseApplication.class, args);
	}

	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CloudBaseApplication.class);
	}*/
}
