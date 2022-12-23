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
    @GetMapping("/naam/allnotice/")
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
    @PostMapping("/naam/addnotice/")
    public JsonResult addNotice(@RequestBody Notice notice){
        if(noticeService.addNotice(notice)){
            return new JsonResult(true, StatusCode.SUCESS,"添加成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"添加失败");
        }

    }

    //修改公告
    @PutMapping("/naam/updatenotice/")
    public JsonResult updateNotice(@RequestBody Notice notice){
        if(noticeService.updateNotice(notice)){
            return new JsonResult(true, StatusCode.SUCESS,"修改成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"修改失败");
        }
    }

    //删除公告
    @DeleteMapping("/naam/removenotice/")
    public JsonResult removeNotice(@RequestBody Notice notice){
        if(noticeService.removeById(notice.getNoticeId())){
            return new JsonResult(true, StatusCode.SUCESS,"删除成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"删除失败");
        }
    }
}
