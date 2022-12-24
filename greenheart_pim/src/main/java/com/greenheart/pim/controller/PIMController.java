package com.greenheart.pim.controller;

import com.greenheart.pim.pojo.Information;
import com.greenheart.pim.pojo.Picture;
import com.greenheart.pim.pojo.User;
import com.greenheart.pim.service.InformationService;
import com.greenheart.pim.service.UserService;
import com.greenheart.pim.util.ObjectAndString;
import entity.JsonResult;
import entity.StatusCode;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@Setter(onMethod_={@Autowired} )
public class PIMController {
    private InformationService informationService;
    private UserService userService;

    //个人信息查看
    @PostMapping("/pim/myself/{userId}")
    public JsonResult myself(@PathVariable String userId){
        User user=userService.myself(userId);
        if(user!=null){
            return new JsonResult(true, StatusCode.SUCESS,"查找成功",user);
        }else {
            return new JsonResult(false, StatusCode.ERROR,"查找失败");
        }
    }
    // 修改个人信息
    @PostMapping("/pim/updatemyself/{userId}/{userName}/{email}/")
    public JsonResult updateMyself(@PathVariable Integer userId,@PathVariable String userName,@PathVariable String email){
        if(userService.updateMyself(userId,userName,email)){
            return new JsonResult(true, StatusCode.SUCESS,"修改成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"修改失败");
        }
    }
    // 修改密码
    @PostMapping("/pim/updatemypwd/{userId}/{userPwd}/")
    public JsonResult updateMyPwd(@PathVariable Integer userId,@PathVariable String userPwd){
        if(userService.updateMyPwd(userId,userPwd)){
            return new JsonResult(true, StatusCode.SUCESS,"修改成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"修改失败");
        }
    }
    // 查看上传的资料
    @PostMapping("/pim/allinformation/{userId}/{informationStatus}/")
    public JsonResult myInformation(@PathVariable String userId,@PathVariable Integer informationStatus){
        ObjectAndString<List<Information>,List<Picture>> informations=informationService.myInformation(userId,informationStatus);
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",informations);
    }

    // 删除上传的资料
    @PostMapping("/pim/removeinformation/{informationId}/")
    public JsonResult removeInformation(@PathVariable String informationId){
        if(informationService.removeInformation(informationId)){
            return new JsonResult(true, StatusCode.SUCESS,"删除成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"删除失败");
        }
    }
    //头像上传
    @PostMapping("/pim/uploda/{userId}/")
    public JsonResult upload(@PathVariable Integer userId, MultipartFile fileImage){
        String result=userService.upload(userId,fileImage);
        if(result.equals("头像上传成功")){
            return new JsonResult(true, StatusCode.SUCESS,"上传成功");
        }else {
            return new JsonResult(false, StatusCode.ERROR,"上传失败",result);
        }
    }
    //头像修改
    @PostMapping("/pim/updatephoto/{userId}")
    public JsonResult updatePhoto(@PathVariable Integer userId, MultipartFile fileImage){
        String result=userService.updatePhoto(userId,fileImage);
        if(result.equals("头像上传成功")){
            return new JsonResult(true, StatusCode.SUCESS,"上传成功");
        }else {
            return new JsonResult(false, StatusCode.ERROR,"上传失败",result);
        }
    }
}
