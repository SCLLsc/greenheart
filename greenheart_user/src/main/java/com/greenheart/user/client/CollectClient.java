package com.greenheart.user.client;

import com.greenheart.user.breaker.CollectBreaker;
import entity.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value="greenheartcollect",fallback = CollectBreaker.class)
public interface CollectClient {

    //查看收藏
    @PostMapping("/collect/{userId}/{pageNum}/")
    public JsonResult selectAllCollect(@PathVariable("userId") Integer userId, @PathVariable("pageNum") Integer pageNum);

    //搜索查看收藏
    @PostMapping("/collect/selectlikeallcollect/{userId}/{like}/")
    public JsonResult selectLikeAllCollect(@PathVariable("userId")  Integer userId,@PathVariable("like")  String like);

    //收藏资料
    @PostMapping("/collect/addcollect/{userId}/{informationId}/")
    public JsonResult addCollect(@PathVariable("userId") Integer userId,@PathVariable("informationId") Integer informationId);

    //取消收藏
    @PostMapping("/collect/removecollect/{userId}/{informationId}/")
    public JsonResult removeById(@PathVariable("userId") Integer userId,@PathVariable("informationId") Integer informationId);

}
