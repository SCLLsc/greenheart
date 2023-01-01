package com.greenheart.user.client;

import com.greenheart.user.breaker.ProcessBreaker;
import entity.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "greenheartprocess",fallback = ProcessBreaker.class)
public interface ProcessClient {

    //查看所有待审核资料
    @PostMapping("/process/allNoInformation/{pageNum}/")
    public JsonResult allNoInformation(@PathVariable("pageNum") Integer pageNum);

    //审核资料
    @PostMapping("/process/{informationId}/{process}/")
    public JsonResult process(@PathVariable("informationId") Integer informationId,@PathVariable("process") Integer process);
}
