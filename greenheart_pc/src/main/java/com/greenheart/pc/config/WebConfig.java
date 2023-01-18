//package com.greenheart.pc.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.support.BasicAuthenticationInterceptor;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.net.Authenticator;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/user/login", "/user/register", "/imserver/**", "/files/**", "/alipay/**",
//                        "/doc.html", "/webjars/**", "/swagger-resources/**");
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//    }
//    @Bean
//    public BasicAuthenticationInterceptor authInterceptor(){return BasicAuthenticationInterceptor();}
//}
