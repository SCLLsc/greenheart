package com.greenheart.ud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.greenheart.ud.dao")
public class UDApplication {
    public static void main(String[] args) {
        SpringApplication.run(UDApplication.class,args);
    }
}
