package com.greenheart.user.client;

import com.greenheart.user.breaker.PCBreaker;
import com.greenheart.user.pojo.Guidance;
import entity.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "greenheartpc",fallback = PCBreaker.class)
public interface PCClient {
    //心理咨询
    @PostMapping("/pc/consult/")
    public JsonResult consult(@RequestBody Guidance guidance);

    //查看心理咨询回复
    @PostMapping("/pc/viewreply/{guidanceId}")
    public JsonResult viewReply(@PathVariable("guidanceId") Integer guidanceId);

    //查看咨询(待回复|已回复)
    @PostMapping("/pc/viewconsultation/{userId}/{guidanceStatus}/{pageNum}/")
    public JsonResult viewConsultation(@PathVariable("userId") Integer userId,@PathVariable("guidanceStatus") Integer guidanceStatus,@PathVariable("pageNum") Integer pageNum);

    //搜索查看咨询(待回复|已回复)
    @PostMapping("/pc/viewlikeconsultation/{userId}/{guidanceStatus}/{like}/")
    public JsonResult viewLikeConsultation(@PathVariable("userId") Integer userId,@PathVariable("guidanceStatus") Integer guidanceStatus,@PathVariable("like") String like);

    //取消咨询
    @PostMapping("/pc/removeguidance/{guidanceId}")
    public JsonResult removeGuidance(@PathVariable("guidanceId") Integer guidanceId);

    //删除已回复的咨询
    @PostMapping("/pc/deleteguidance/{guidanceId}/")
    public JsonResult delGuidance(@PathVariable("guidanceId") Integer guidanceId);
}
