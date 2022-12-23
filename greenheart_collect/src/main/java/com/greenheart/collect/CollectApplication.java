package com.greenheart.collect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class CollectApplication {
    public static void main(String[] args) {
        SpringApplication.run(CollectApplication.class,args);
    }
}
