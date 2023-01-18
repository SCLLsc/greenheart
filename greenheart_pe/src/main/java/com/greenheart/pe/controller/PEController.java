package com.greenheart.pe.controller;

import com.greenheart.pe.pojo.Mark;
import com.greenheart.pe.pojo.Trial;
import com.greenheart.pe.service.MarkService;
import com.greenheart.pe.service.TrialService;
import com.greenheart.pe.util.ObjectAndString;
import entity.JsonResult;
import entity.StatusCode;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@Setter(onMethod_={@Autowired} )
public class PEController {
    private TrialService trialService;
    private MarkService markService;

    //心理评测
    @PostMapping("/pe/evaluating/{userId}/{trialTitle}/")
    public JsonResult evaluating(@PathVariable Integer userId,@PathVariable String trialTitle, @RequestBody List<String[]> answers){
        boolean flag=trialService.evaluating(userId,trialTitle,answers);
        if(flag){
            return new JsonResult(true, StatusCode.SUCESS,"完成测试");
        }else {
            return new JsonResult(false, StatusCode.ERROR,"测试失败");
        }
    }
    //查看成绩
    @PostMapping("pe/viewscore/{userId}/{pageNum}/")
    public JsonResult viewScore(@PathVariable Integer userId,@PathVariable Integer pageNum){
        ObjectAndString<List<Mark>,Integer> marks= markService.viewScore(userId,pageNum);
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",marks);
    }
    //搜索查看成绩
    @PostMapping("pe/viewlikescore/{userId}/{like}/")
    public JsonResult viewLikeScore(@PathVariable Integer userId,@PathVariable String like){
        ObjectAndString<List<Mark>,Integer> marks= markService.viewLikeScore(userId,like);
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
