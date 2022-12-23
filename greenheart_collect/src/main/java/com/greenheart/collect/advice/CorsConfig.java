package com.greenheart.collect.advice;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootConfiguration
public class CorsConfig {

    private CorsConfiguration buildConfig(){
        CorsConfiguration corsConfiguration=new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");//表示支持所有来源的请求跨域
        corsConfiguration.addAllowedMethod("*");//表示支持所有方法的请求跨域
        corsConfiguration.addAllowedHeader("*");//表示是允许改动的请求头
        return corsConfiguration;
    }
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",buildConfig());
        return new CorsFilter(source);
    }



}

