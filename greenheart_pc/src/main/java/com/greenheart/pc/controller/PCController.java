package com.greenheart.pc.controller;

import com.greenheart.pc.pojo.Guidance;
import com.greenheart.pc.pojo.Reply;
import com.greenheart.pc.service.GuidanceService;
import com.greenheart.pc.service.ReplyService;
import entity.JsonResult;
import entity.StatusCode;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@Setter(onMethod_={@Autowired} )
public class PCController {
    private GuidanceService guidanceService;
    private ReplyService replyService;

    //心理咨询
    @PostMapping("/pc/consult/{userId}/{guidanceContent}")
    public JsonResult consult(@PathVariable Integer userId,@PathVariable String guidanceContent){
        if(guidanceService.consult(userId,guidanceContent)){
            return new JsonResult(true, StatusCode.SUCESS,"咨询成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"咨询失败");
        }
    }
    //查看心理咨询回复
    @PostMapping("/pc/viewreply/{guidanceId}")
    public JsonResult viewReply(@PathVariable Integer guidanceId){
        Reply reply=replyService.viewReply(guidanceId);
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",reply);
    }
    //查看咨询(待回复|已回复)
    @PostMapping("/pc/viewconsultation/{userId}/{guidanceStatus}/")
    public JsonResult viewConsultation(@PathVariable Integer userId,@PathVariable Integer guidanceStatus){
        List<Guidance> guidances= guidanceService.viewConsultation(userId, guidanceStatus);
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",guidances);
    }
    //取消咨询
    @PostMapping("/pc/removeguidance/{guidanceId}")
    public JsonResult removeGuidance(@PathVariable Integer guidanceId){
        boolean flag=guidanceService.removeById(guidanceId);
        if(flag){
            return new JsonResult(true, StatusCode.SUCESS,"取消成功");
        }else {
            return new JsonResult(false, StatusCode.ERROR,"取消失败");
        }
    }
    //删除已回复的咨询
    @PostMapping("/pc/deleteguidance/{guidanceId}/")
    public JsonResult delGuidance(@PathVariable Integer guidanceId){
        boolean flag=guidanceService.delGuidance(guidanceId);
        if(flag){
            return new JsonResult(true, StatusCode.SUCESS,"取消成功");
        }else {
            return new JsonResult(false, StatusCode.ERROR,"取消失败");
        }
    }
}
