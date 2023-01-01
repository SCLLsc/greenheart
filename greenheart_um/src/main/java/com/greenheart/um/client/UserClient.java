package com.greenheart.um.client;

import com.greenheart.um.breaker.UserBreaker;
import entity.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@FeignClient(value = "greenheartum",fallback = UserBreaker.class)
public interface UserClient {
    //登录
    @PostMapping("/user/login/{email}/{userPwd}/")
    public JsonResult login(@PathVariable String email, @PathVariable String userPwd, HttpSession session);

    //登出
    @PostMapping("/user/logout/")
    public JsonResult logout(HttpSession session);
}
