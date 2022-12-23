package com.greenheart.pc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PCApplication {
    public static void main(String[] args) {
        SpringApplication.run(PCApplication.class,args);
    }
}
