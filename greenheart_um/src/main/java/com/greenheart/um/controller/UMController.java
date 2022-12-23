package com.greenheart.um.controller;

import com.greenheart.um.pojo.Guidance;
import com.greenheart.um.pojo.Reply;
import com.greenheart.um.service.ReplyService;
import entity.JsonResult;
import entity.StatusCode;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Setter(onMethod_ = {@Autowired})
public class UMController {
    private ReplyService replyService;

    //查看咨询(待回复|已回复)
    @PostMapping("/um/viewconsultation/{guidanceStatus}/{pageNum}/")
    public JsonResult viewConsultation(@PathVariable Integer guidanceStatus,@PathVariable Integer pageNum){
        List<Guidance> guidances= replyService.viewConsultation( guidanceStatus, pageNum);
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",guidances);
    }
    //查看单个咨询未回复内容
    @PostMapping("/um/viewoneconsultation/{guidanceId}/")
    public JsonResult viewOneConsultation(@PathVariable Integer guidanceId){
        Guidance guidance= replyService.viewOneConsultation(guidanceId);
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",guidance);
    }
    //心理咨询回复
    @PostMapping("/um/pcreply/")
    public JsonResult pcReply(@RequestBody Reply pcReply){
        if(replyService.pcReply(pcReply)){
            return new JsonResult(true, StatusCode.SUCESS,"回复成功");
        }else {
            return new JsonResult(false, StatusCode.ERROR,"回复失败");
        }
    }

    //删除用户
    @DeleteMapping("/um/removeuser/{userId}/")
    public JsonResult removeUser(@PathVariable Integer userId){
        replyService.cancellation(userId);
        return new JsonResult(true, StatusCode.SUCESS,"删除成功");
    }

}
