package com.greenheart.um.controller;

import com.greenheart.um.pojo.Guidance;
import com.greenheart.um.pojo.Reply;
import com.greenheart.um.pojo.User;
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

    //查看所有用户
    @PostMapping("/um/viewalluser/")
    public JsonResult viewAllUser(){
        List<User> users= replyService.viewAllUser();
        if(users!=null){
            return new JsonResult(true, StatusCode.SUCESS,"查找成功",users);
        }else {
            return new JsonResult(false, StatusCode.ERROR,"查找失败");
        }
    }
    //查看咨询(待回复|已回复)
    @PostMapping("/um/viewconsultation/{guidanceStatus}/")
    public JsonResult viewConsultation(@PathVariable Integer guidanceStatus){
        List<Guidance> guidances= replyService.viewConsultation(guidanceStatus);
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",guidances);
    }
    //查看单个咨询未回复内容
    @PostMapping("/um/viewoneconsultation/{guidanceId}/")
    public JsonResult viewOneConsultation(@PathVariable Integer guidanceId){
        Guidance guidance= replyService.viewOneConsultation(guidanceId);
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",guidance);
    }
    //心理咨询回复
    @PostMapping("/um/pcreply/{guidanceId}/{replyContent}/")
    public JsonResult pcReply(@PathVariable Integer guidanceId,@PathVariable String replyContent){
        if(replyService.pcReply(guidanceId,replyContent)){
            return new JsonResult(true, StatusCode.SUCESS,"回复成功");
        }else {
            return new JsonResult(false, StatusCode.ERROR,"回复失败");
        }
    }

    //删除用户
    @PostMapping("/um/removeuser/{userId}/")
    public JsonResult removeUser(@PathVariable Integer userId){
        replyService.cancellation(userId);
        return new JsonResult(true, StatusCode.SUCESS,"删除成功");
    }

}
