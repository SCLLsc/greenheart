package com.greenheart.user.client;

import com.greenheart.user.breaker.PIMBreaker;
import entity.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "greenheartpim",fallback = PIMBreaker.class)
public interface PIMClient {
    //个人信息查看
    @PostMapping("/pim/myself/{userId}/")
    public JsonResult myself(@PathVariable("userId") String userId);

    // 修改个人信息
    @PostMapping("/pim/updatemyself/{userId}/{userName}/{email}/")
    public JsonResult updateMyself(@PathVariable("userId") Integer userId,@PathVariable("userName") String userName,@PathVariable("email") String email);

    // 修改密码
    @PostMapping("/pim/updatemypwd/{userId}/{userPwd}/")
    public JsonResult updateMyPwd(@PathVariable("userId") Integer userId,@PathVariable("userPwd") String userPwd);

    // 查看上传的资料
    @PostMapping("/pim/allinformation/{userId}/{informationStatus}/{pageNum}/")
    public JsonResult myInformation(@PathVariable("userId") String userId,@PathVariable("informationStatus") Integer informationStatus,@PathVariable("pageNum") Integer pageNum);

    //搜索查看上传的资料
    @PostMapping("/pim/mylikeinformation/{userId}/{informationStatus}/{like}/")
    public JsonResult myLikeInformation(@PathVariable("userId") String userId,@PathVariable("informationStatus") Integer informationStatus,@PathVariable("like") String like);

    // 删除上传的资料
    @PostMapping("/pim/removeinformation/{informationId}/")
    public JsonResult removeInformation(@PathVariable("informationId") String informationId);
}
