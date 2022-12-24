package com.greenheart.process.controller;


import com.greenheart.process.pojo.Information;
import com.greenheart.process.pojo.Picture;
import com.greenheart.process.service.InformationService;
import com.greenheart.process.util.ObjectAndString;
import entity.JsonResult;
import entity.StatusCode;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@Setter(onMethod_= {@Autowired})
public class ProcessController {
    private InformationService informationService;

    //查看所有待审核资料
    @PostMapping("/process/allNoInformation/{pageNum}/")
    public JsonResult allNoInformation(@PathVariable Integer pageNum){
           ObjectAndString<List<Information>,List<Picture>> result= informationService.allNoInformation(pageNum);
            return new JsonResult(true, StatusCode.SUCESS,"查找成功",result);

    }
    //审核资料
    @PostMapping("/process/{informationId}/{process}/")
    public JsonResult process(@PathVariable Integer informationId,@PathVariable Integer process){
        if(informationService.process(informationId,process)){
            return new JsonResult(true, StatusCode.SUCESS,"审核完成：通过");
        }else {
            return new JsonResult(true, StatusCode.SUCESS,"审核完成：未通过");
        }
    }
}
