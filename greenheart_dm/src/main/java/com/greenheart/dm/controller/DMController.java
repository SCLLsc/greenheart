package com.greenheart.dm.controller;

import com.greenheart.dm.pojo.Information;
import com.greenheart.dm.pojo.Picture;
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
        ObjectAndString<List<Information>,List<Picture>> informations= informationService.allInformation(pageNum);
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",informations);
    }
    //查看资料
    @PostMapping("/dm/findinformationById/{informationId}/")
    public JsonResult findInformationBy(@PathVariable Integer informationId){
        ObjectAndString<Information,Picture> informations= informationService.informationId(informationId);
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",informations);
    }
    //查看心理评测
    @PostMapping("/dm/selectalltrial/{pageNum}/")
    public JsonResult selectAllTrial(@PathVariable Integer pageNum){
        List<Trial> trials=trialService.selectAllTrial(pageNum);
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",trials);
    }
    //查看心理评测试题
    @PostMapping("/dm/selecttrialbytitle/{trialTitle}/")
    public JsonResult selectTrialByTitle(@PathVariable String trialTitle){
        List<Trial> trial=trialService.selectTrialByTitle(trialTitle);
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",trial);
    }
    //查看单个心理评测试题
    @PostMapping("/dm/selecttrialbyid/{trialId}/")
    public JsonResult selectTrialById(@PathVariable Integer trialId){
        Trial trial =trialService.query().eq("trial_id",trialId).one();
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",trial);
    }
    //增加单个心理评测题目
//    @PostMapping("/dm/addtrialone/")
//    public JsonResult addTrialOne(@RequestBody Trial trial){
//        System.out.println(trial);
//        if(trialService.addTrialOne(trial)){
//            return new JsonResult(true, StatusCode.SUCESS,"增加成功");
//        }else{
//            return new JsonResult(false, StatusCode.ERROR,"增加失败");
//        }
//    }

    //增加心理评测
    @PostMapping("/dm/addtrial/")
    public JsonResult addTrial(@RequestBody List<Trial> trial){
        System.out.println(trial);
        if(trialService.addTrial(trial)){
            return new JsonResult(true, StatusCode.SUCESS,"增加成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"增加失败");
        }
    }
    //删除单个心理评测
    @PostMapping("/dm/removetrialbyid/{trialId}/")
    public JsonResult removeTrialById(@PathVariable Integer trialId){
        if(trialService.removeById(trialId)){
            return new JsonResult(true, StatusCode.SUCESS,"删除成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"删除失败");
        }
    }
    //删除心理评测
    @PostMapping("/dm/removetrial/{trialTitle}/")
    public JsonResult removeTrial(@PathVariable String trialTitle){
        if(trialService.removeTrial(trialTitle)){
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
    //修改心理评测时间
    @PostMapping("/dm/updatetrialcycle/{trialTitle}/{cycle}/")
    public JsonResult updateTrialCycle(@PathVariable String trialTitle,@PathVariable Integer cycle){
        if(trialService.updateTrialCycle(trialTitle,cycle)){
            return new JsonResult(true, StatusCode.SUCESS,"修改成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"修改失败");
        }
    }


}
