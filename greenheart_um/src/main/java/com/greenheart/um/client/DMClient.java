package com.greenheart.um.client;

import com.greenheart.um.breaker.DMBreaker;
import com.greenheart.um.pojo.Trial;
import entity.JsonResult;
import entity.StatusCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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
    @PostMapping("/dm/addtrialone/{trialNum}/")
    public JsonResult addTrialOne(@PathVariable("trialNum") Integer trialNum,@RequestBody Trial trial);

    //增加心理评测
    @PostMapping("/dm/addtrial/{userId}/")
    public JsonResult addTrial(@PathVariable("userId") Integer userId,@RequestBody Trial trial);

    //删除单个心理评测
    @PostMapping("/dm/removetrialbyid/{trialId}/")
    public JsonResult removeTrialById(@PathVariable("trialId") Integer trialId);

    //删除心理评测
    @PostMapping("/dm/removetrial/{trialTitle}/")
    public JsonResult removeTrial(@PathVariable("trialTitle") String trialTitle);

    //修改心理评测
    @PostMapping("/dm/updatetrial/")
    public JsonResult updateTrial(@RequestBody Trial trial);

    //修改心理评测时间
    @PostMapping("/dm/updatetrialcycle/")
    public JsonResult updateTrialCycle(@RequestBody Trial trial);

    //修改Excel心理评测
    @PostMapping("/dm/updatealltrial/")
    public JsonResult updateAllTrial(@RequestBody List<Trial> trial);
}
