package com.greenheart.naam;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NAAMApplication {
    public static void main(String[] args) {
        SpringApplication.run(NAAMApplication.class,args);
    }
}
