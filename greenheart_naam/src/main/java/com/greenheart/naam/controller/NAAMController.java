package com.greenheart.naam.controller;

import com.greenheart.naam.pojo.Notice;
import com.greenheart.naam.service.NoticeService;
import entity.JsonResult;
import entity.StatusCode;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Setter(onMethod_={@Autowired} )
public class NAAMController {
    private NoticeService noticeService;

    //查看公告
    @PostMapping("/naam/allnotice/")
    public JsonResult allNotice(){
        List<Notice> notices= noticeService.allNotice();
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",notices);
    }

    //查看单个公告
    @PostMapping("/naam/onenotice/{noticeId}/")
    public JsonResult oneNotice(@PathVariable Integer noticeId){
        Notice notice= noticeService.getById(noticeId);
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",notice);
    }
    //新增公告
    @PostMapping("/naam/addnotice/{noticeTitle}/{noticeContent}/")
    public JsonResult addNotice(@PathVariable String noticeTitle,@PathVariable String noticeContent){
        if(noticeService.addNotice(noticeTitle,noticeContent)){
            return new JsonResult(true, StatusCode.SUCESS,"添加成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"添加失败");
        }

    }

    //修改公告
    @PostMapping("/naam/updatenotice/{noticeId}/{noticeTitle}/{noticeContent}/")
    public JsonResult updateNotice(@PathVariable Integer noticeId,@PathVariable String noticeTitle,@PathVariable String noticeContent){
        if(noticeService.updateNotice(noticeId,noticeTitle,noticeContent)){
            return new JsonResult(true, StatusCode.SUCESS,"修改成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"修改失败");
        }
    }

    //删除公告
    @PostMapping("/naam/removenotice/{noticeId}/")
    public JsonResult removeNotice(@PathVariable Integer noticeId){
        if(noticeService.removeById(noticeId)){
            return new JsonResult(true, StatusCode.SUCESS,"删除成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"删除失败");
        }
    }
}
