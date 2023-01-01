package com.greenheart.um.client;

import com.greenheart.um.breaker.DMBreaker;
import entity.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "greenheartdm",fallback = DMBreaker.class)
public interface DMClient {
    //查看所有的资料
    @PostMapping("/dm/allinformation/{pageNum}/")
    public JsonResult allInformation(@PathVariable("pageNum") Integer pageNum);

    //搜索资料
    @PostMapping("/dm/alllikeinformation/{like}/")
    public JsonResult allLikeInformation(@PathVariable("like") String like);

    //查看资料
    @PostMapping("/dm/findinformationById/{informationId}/")
    public JsonResult findInformationBy(@PathVariable("informationId") Integer informationId);

    //查看心理评测
    @PostMapping("/dm/selectalltrial/{pageNum}/")
    public JsonResult selectAllTrial(@PathVariable("pageNum") Integer pageNum);

    //搜索查看心理评测
    @PostMapping("/dm/selectlikealltrial/{like}/")
    public JsonResult selectLikeAllTrial(@PathVariable("like") String like);

    //开始做心理评测试题
    @PostMapping("/dm/starttrialbytitle/{trialTitle}/")
    public JsonResult startTrialByTitle(@PathVariable("trialTitle") String trialTitle);

    //查看心理评测试题
    @PostMapping("/dm/selecttrialbytitle/{trialTitle}/{pageNum}/")
    public JsonResult selectTrialByTitle(@PathVariable("trialTitle") String trialTitle,@PathVariable("pageNum") Integer pageNum);

    //搜索查看心理评测试题
    @PostMapping("/dm/selectliketrialbytitle/{trialTitle}/{like}/")
    public JsonResult selectLikeTrialByTitle(@PathVariable("trialTitle") String trialTitle,@PathVariable("like") String like);

    //查看单个心理评测试题
    @PostMapping("/dm/selecttrialbyid/{trialId}/")
    public JsonResult selectTrialById(@PathVariable("trialId") Integer trialId);

    //增加单个心理评测题目
    @PostMapping("/dm/addtrialone/{userId}/{trialType}/{trialTitle}/{trialContent}/{trialAnswer}/{trialScore}/{cycle}/")
    public JsonResult addTrialOne(@PathVariable("userId") Integer userId,@PathVariable("trialType") String trialType,@PathVariable("trialTitle") String trialTitle,@PathVariable("trialContent") String trialContent,@PathVariable("trialAnswer") String trialAnswer,@PathVariable("trialScore") Integer trialScore,@PathVariable("cycle") Integer cycle);

    //增加心理评测
    @PostMapping("/dm/addtrial/{userId}/{trialType}/{trialTitle}/{cycle}/")
    public JsonResult addTrial(@PathVariable("userId") Integer userId,@PathVariable("trialType") String trialType,@PathVariable("trialTitle") String trialTitle,@PathVariable("cycle") Integer cycle);

    //删除单个心理评测
    @PostMapping("/dm/removetrialbyid/{trialId}/")
    public JsonResult removeTrialById(@PathVariable("trialId") Integer trialId);

    //删除心理评测
    @PostMapping("/dm/removetrial/{trialTitle}/")
    public JsonResult removeTrial(@PathVariable("trialTitle") String trialTitle);

    //修改心理评测
    @PostMapping("/dm/updatetrial/{trialId}/{trialContent}/{trialAnswer}/{trialScore}")
    public JsonResult updateTrial(@PathVariable("trialId") Integer trialId,@PathVariable("trialContent") String trialContent,@PathVariable("trialAnswer") String trialAnswer,@PathVariable("trialScore") Integer trialScore);

    //修改心理评测时间
    @PostMapping("/dm/updatetrialcycle/{trialTitle}/{cycle}/")
    public JsonResult updateTrialCycle(@PathVariable("trialTitle") String trialTitle,@PathVariable("cycle") Integer cycle);
}
