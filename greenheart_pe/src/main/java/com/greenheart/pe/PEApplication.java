package com.greenheart.pe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PEApplication {
    public static void main(String[] args) {
        SpringApplication.run(PEApplication.class,args);
    }
}
