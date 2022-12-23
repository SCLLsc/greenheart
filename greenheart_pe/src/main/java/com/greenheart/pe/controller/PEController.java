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
    @PostMapping("/pe/evaluating/{userId}/{trialTitle}/")
    public JsonResult evaluating(@PathVariable Integer userId,@PathVariable String trialTitle, @RequestBody List<String> answers){
        trialService.evaluating(userId,trialTitle,answers);
        return new JsonResult(true, StatusCode.SUCESS,"完成测试");
    }
    //查看成绩
    @PostMapping("pe/viewscore/{userId}/{pageNum}/")
    public JsonResult viewScore(@PathVariable Integer userId,@PathVariable Integer pageNum){
        List<Mark> marks= markService.viewScore(userId, pageNum);
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",marks);
    }
}
