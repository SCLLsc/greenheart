package com.greenheart.um.client;

import com.greenheart.um.breaker.ProcessBreaker;
import entity.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "greenheartprocess",fallback = ProcessBreaker.class)
public interface ProcessClient {

    //查看所有待审核资料
    @PostMapping("/process/allNoInformation/{pageNum}/")
    public JsonResult allNoInformation(@PathVariable("pageNum") Integer pageNum);

    //搜索待审核资料
    @PostMapping("/process/allLikeNoInformation/{like}/")
    public JsonResult allLikeNoInformation(@PathVariable("like") String like);

    //审核资料
    @PostMapping("/process/{informationId}/{process}/")
    public JsonResult process(@PathVariable("informationId") Integer informationId,@PathVariable("process") Integer process);
}
