package com.greenheart.um.client;

import com.greenheart.um.breaker.PEBreaker;
import entity.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "greenheartpe",fallback = PEBreaker.class)
public interface PEClient {
    //心理评测
    @PostMapping("/pe/evaluating/{userId}/{trialTitle}/")
    public JsonResult evaluating(@PathVariable("userId") Integer userId, @PathVariable("trialTitle") String trialTitle, @RequestBody List<String> answers);

    //查看成绩
    @PostMapping("pe/viewscore/{userId}/{pageNum}/")
    public JsonResult viewScore(@PathVariable("userId") Integer userId,@PathVariable("pageNum") Integer pageNum);

    //搜索查看成绩
    @PostMapping("pe/viewlikescore/{userId}/{like}/")
    public JsonResult viewLikeScore(@PathVariable("userId") Integer userId,@PathVariable("like") String like);

    //删除成绩
    @PostMapping("pe/delscore/{markId}/")
    public JsonResult delScore(@PathVariable("markId") Integer markId);
}
