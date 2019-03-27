package com.insigma.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 */
@SpringBootApplication
public class Oauth2Application {

    public static void main(String[] args){
        new SpringApplication(Oauth2Application.class).run(args);
    }
}
