package com.greenheart.ud.controller;

import com.greenheart.ud.pojo.Information;
import com.greenheart.ud.service.InformationService;
import com.greenheart.ud.util.ObjectAndString;
import entity.JsonResult;
import entity.StatusCode;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Setter(onMethod_ ={@Autowired} )
public class UDController {
    private InformationService informationService;

    //上传资料
    @PostMapping("/ud/information/upload/")
    public JsonResult upload(@RequestBody ObjectAndString<Information,String> information, MultipartFile fileImage){
        if(informationService.upload(information,fileImage).equals("资料上传成功")){
            return new JsonResult(true, StatusCode.SUCESS,"上传成功");
        }else {
            return new JsonResult(false, StatusCode.ERROR,"上传失败");
        }
    }
    //上传资料
    @PostMapping("/ud/information/upload/{userId}/{informationType}/{informationTitle}/{informationContent}/")
    public JsonResult uploadInformation(@PathVariable Integer userId, @PathVariable String informationType, @PathVariable String informationTitle, @PathVariable String informationContent){
        if(informationService.uploadInformation(userId,informationType,informationTitle,informationContent).equals("资料上传成功")){
            return new JsonResult(true, StatusCode.SUCESS,"上传成功");
        }else {
            return new JsonResult(false, StatusCode.ERROR,"上传失败");
        }
    }
}
