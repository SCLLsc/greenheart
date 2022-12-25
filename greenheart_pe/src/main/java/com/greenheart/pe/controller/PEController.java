package com.greenheart.pe.controller;

import com.greenheart.pe.pojo.Mark;
import com.greenheart.pe.pojo.Trial;
import com.greenheart.pe.service.MarkService;
import com.greenheart.pe.service.TrialService;
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
public class PEController {
    private TrialService trialService;
    private MarkService markService;

    //心理评测
    @PostMapping("/pe/evaluating/{userId}/{trialTitle}/{markScore}/")
    public JsonResult evaluating(@PathVariable Integer userId,@PathVariable String trialTitle, @PathVariable Integer markScore){
        boolean flag=trialService.evaluating(userId,trialTitle,markScore);
        if(flag){
            return new JsonResult(true, StatusCode.SUCESS,"完成测试");
        }else {
            return new JsonResult(false, StatusCode.ERROR,"测试失败");
        }
    }
    //查看成绩
    @PostMapping("pe/viewscore/{userId}/")
    public JsonResult viewScore(@PathVariable Integer userId){
        List<Mark> marks= markService.viewScore(userId);
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",marks);
    }
    //删除成绩
    @PostMapping("pe/delscore/{markId}/")
    public JsonResult delScore(@PathVariable Integer markId){
        boolean flag= markService.removeById(markId);
        if(flag){
            return new JsonResult(true, StatusCode.SUCESS,"删除成功");
        }else {
            return new JsonResult(true, StatusCode.SUCESS,"删除失败");
        }

    }
}
