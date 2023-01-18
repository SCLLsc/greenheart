package com.greenheart.dm.controller;

import com.greenheart.dm.pojo.Answer;
import com.greenheart.dm.pojo.Information;
import com.greenheart.dm.pojo.Trial;
import com.greenheart.dm.service.InformationService;
import com.greenheart.dm.service.TrialService;
import com.greenheart.dm.util.ObjectAndString;
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
public class DMController {
    private InformationService informationService;
    private TrialService trialService;

    //查看所有的资料
    @PostMapping("/dm/allinformation/{pageNum}/")
    public JsonResult allInformation(@PathVariable Integer pageNum){
        ObjectAndString<List<Information>,Integer> informations= informationService.allInformation(pageNum);
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",informations);
    }
    //搜索资料
    @PostMapping("/dm/alllikeinformation/{like}/")
    public JsonResult allLikeInformation(@PathVariable String like){
        ObjectAndString<List<Information>,Integer> informations= informationService.allLikeInformation(like);
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",informations);
    }
    //查看资料
    @PostMapping("/dm/findinformationById/{informationId}/")
    public JsonResult findInformationBy(@PathVariable Integer informationId){
        Information informations= informationService.informationId(informationId);
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",informations);
    }
    //查看心理评测
    @PostMapping("/dm/selectalltrial/{pageNum}/")
    public JsonResult selectAllTrial(@PathVariable Integer pageNum){
        ObjectAndString<List<Trial>,Integer> trials=trialService.selectAllTrial(pageNum);
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",trials);
    }
    //搜索查看心理评测
    @PostMapping("/dm/selectlikealltrial/{like}/")
    public JsonResult selectLikeAllTrial(@PathVariable String like){
        ObjectAndString<List<Trial>,Integer> trials=trialService.selectLikeAllTrial(like);
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",trials);
    }
    //查看心理评测试题
    @PostMapping("/dm/selecttrialbytitle/{trialTitle}/{pageNum}/")
    public JsonResult selectTrialByTitle(@PathVariable String trialTitle,@PathVariable Integer pageNum){
        ObjectAndString<List<Trial>,Integer> trial=trialService.selectTrialByTitle(trialTitle,pageNum);
        if(trial!=null){
            return new JsonResult(true, StatusCode.SUCESS,"查找成功",trial);
        }else {
            return new JsonResult(false, StatusCode.ERROR,"查找失败");
        }
    }
    //开始做心理评测试题
    @PostMapping("/dm/starttrialbytitle/{trialTitle}/")
    public JsonResult startTrialByTitle(@PathVariable String trialTitle){
        List<Trial> trial=trialService.startTrialByTitle(trialTitle);
        if(trial!=null){
            return new JsonResult(true, StatusCode.SUCESS,"查找成功",trial);
        }else {
            return new JsonResult(false, StatusCode.ERROR,"查找失败");
        }
    }
    //搜索查看心理评测试题
    @PostMapping("/dm/selectliketrialbytitle/{trialTitle}/{like}/")
    public JsonResult selectLikeTrialByTitle(@PathVariable String trialTitle,@PathVariable String like){
        ObjectAndString<List<Trial>,Integer> trial=trialService.selectLikeTrialByTitle(trialTitle,like);
        if(trial!=null){
            return new JsonResult(true, StatusCode.SUCESS,"查找成功",trial);
        }else {
            return new JsonResult(false, StatusCode.ERROR,"查找失败");
        }
    }
    //查看单个心理评测试题
    @PostMapping("/dm/selecttrialbyid/{trialId}/")
    public JsonResult selectTrialById(@PathVariable Integer trialId){
        Trial trial =trialService.query().eq("trial_id",trialId).one();
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",trial);
    }
    //增加单个心理评测题目
    @PostMapping("/dm/addtrialone/{trialNum}/")
    public JsonResult addTrialOne(@PathVariable Integer trialNum,@RequestBody Trial trial){
        if(trialService.addTrialOne(trialNum,trial)){
            return new JsonResult(true, StatusCode.SUCESS,"增加成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"增加失败");
        }
    }

    //增加心理评测
    @PostMapping("/dm/addtrial/{userId}/")
    public JsonResult addTrial(@PathVariable Integer userId,@RequestBody Trial trial){
        if(trialService.addTrial(userId,trial)){
            return new JsonResult(true, StatusCode.SUCESS,"增加成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"该试卷已存在，增加失败");
        }
    }
    //删除单个心理评测
    @PostMapping("/dm/removetrialbyid/{trialId}/")
    public JsonResult removeTrialById(@PathVariable Integer trialId){
        boolean flag=trialService.removeOneById(trialId);
        if(flag){
            return new JsonResult(true, StatusCode.SUCESS,"删除成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"删除失败");
        }
    }
    //删除心理评测
    @PostMapping("/dm/removetrial/{trialTitle}/")
    public JsonResult removeTrial(@PathVariable String trialTitle){
        boolean flag=trialService.removeTrial(trialTitle);
        if(flag){
            return new JsonResult(true, StatusCode.SUCESS,"删除成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"删除失败");
        }
    }

   //修改心理评测
   @PostMapping("/dm/updatetrial/")
   public JsonResult updateTrial(@RequestBody Trial trial){
       if(trialService.updateTrial(trial)){
           return new JsonResult(true, StatusCode.SUCESS,"修改成功");
       }else{
           return new JsonResult(false, StatusCode.ERROR,"修改失败");
       }
   }
    //添加Excel心理评测
    @PostMapping("/dm/addexcelalltrial/{userId}/")
    public JsonResult addExcelAllTrial(@PathVariable Integer userId,@RequestBody List<Trial> trial){
        if(trialService.addExcelAllTrial(userId,trial)){
            return new JsonResult(true, StatusCode.SUCESS,"上传题目成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"上传题目失败，该标题试卷已存在，请修改后在上传");
        }
    }
    //添加Excel心理评测答案
    @PostMapping("/dm/addallanswer/{trialTitle}/")
    public JsonResult addAllAnswer(@PathVariable String trialTitle,@RequestBody List<Answer> Answer){
        if(trialService.addAllAnswer(trialTitle,Answer)){
            return new JsonResult(true, StatusCode.SUCESS,"上传答案成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"已存在答案，上传答案失败，请点击修改答案导入修改后的文件");
        }
    }
    //修改Excel心理评测
    @PostMapping("/dm/updatealltrial/")
    public JsonResult updateAllTrial(@RequestBody List<Trial> trial){
        if(trialService.updateAllTrial(trial)){
            return new JsonResult(true, StatusCode.SUCESS,"修改成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"修改失败");
        }
    }
    //查找单个答案
    @PostMapping("/dm/findanswerbyid/{trialId}/")
    public JsonResult findAnswerById(@PathVariable Integer trialId){
        Answer answer= trialService.findAnswerById(trialId);
        if(answer!=null){
            return new JsonResult(true, StatusCode.SUCESS,"查找成功",answer);
        }else{
            return new JsonResult(false, StatusCode.ERROR,"查找失败");
        }
    }
    //查找所有答案
    @PostMapping("/dm/findallanswer/{trialTitle}/")
    public JsonResult findAllAnswer(@PathVariable String trialTitle){
        List<Answer> answer= trialService.findAllAnswer(trialTitle);
        if(answer.size()!=0){
            return new JsonResult(true, StatusCode.SUCESS,"查找成功",answer);
        }else{
            return new JsonResult(false, StatusCode.ERROR,"查找失败");
        }
    }
    //修改单个答案
    @PostMapping("/dm/updateanswer/")
    public JsonResult updateAnswer(@RequestBody Answer answer){
        if(trialService.updateAnswer(answer)){
            return new JsonResult(true, StatusCode.SUCESS,"修改成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"修改失败");
        }
    }
    //修改所有答案
    @PostMapping("/dm/updateallanswer/{trialTitle}/")
    public JsonResult updateAllAnswer(@PathVariable String trialTitle,@RequestBody List<Answer> answer){
        if(trialService.updateAllAnswer(trialTitle,answer)){
            return new JsonResult(true, StatusCode.SUCESS,"修改成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"还未导入过答案,修改失败");
        }
    }
    //修改心理评测时间
    @PostMapping("/dm/updatetrialcycle/")
    public JsonResult updateTrialCycle(@RequestBody Trial trial){
        if(trialService.updateTrialCycle(trial)){
            return new JsonResult(true, StatusCode.SUCESS,"修改成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"修改失败");
        }
    }


}
