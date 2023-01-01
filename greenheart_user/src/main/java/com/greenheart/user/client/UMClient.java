package com.greenheart.user.client;

import com.greenheart.user.breaker.UMBreaker;
import entity.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "greenheartum",fallback = UMBreaker.class)
public interface UMClient {
    //删除用户
    @PostMapping("/um/removeuser/{userId}/")
    public JsonResult removeUser(@PathVariable("userId") Integer userId);
}
