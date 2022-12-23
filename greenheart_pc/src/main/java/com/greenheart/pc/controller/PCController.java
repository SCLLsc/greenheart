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
    @PostMapping("/pc/consult/")
    public JsonResult consult(@RequestBody Guidance guidance){
        if(guidanceService.consult(guidance)){
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
    @PostMapping("/pc/viewconsultation/{userId}/{guidanceStatus}/{pageNum}")
    public JsonResult viewConsultation(@PathVariable Integer userId,@PathVariable Integer guidanceStatus,@PathVariable Integer pageNum){
        List<Guidance> guidances= guidanceService.viewConsultation(userId, guidanceStatus, pageNum);
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",guidances);
    }
}
