package com.greenheart.user.client;

import com.greenheart.user.breaker.PIMBreaker;
import com.greenheart.user.pojo.User;
import entity.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "greenheartpim",fallback = PIMBreaker.class)
public interface PIMClient {
    //个人信息查看
    @PostMapping("/pim/myself/{userId}/")
    public JsonResult myself(@PathVariable("userId") String userId);

    // 修改个人信息
    @PostMapping("/pim/updatemyself/")
    public JsonResult updateMyself(@RequestBody User user);

    // 修改密码
    @PostMapping("/pim/updatemypwd/")
    public JsonResult updateMyPwd(@RequestBody User user);

    // 查看上传的资料
    @PostMapping("/pim/allinformation/{userId}/{informationStatus}/{pageNum}/")
    public JsonResult myInformation(@PathVariable("userId") String userId,@PathVariable("informationStatus") Integer informationStatus,@PathVariable("pageNum") Integer pageNum);

    //搜索查看上传的资料
    @PostMapping("/pim/mylikeinformation/{userId}/{informationStatus}/{like}/")
    public JsonResult myLikeInformation(@PathVariable("userId") String userId,@PathVariable("informationStatus") Integer informationStatus,@PathVariable("like") String like);

    // 删除上传的资料
    @PostMapping("/pim/removeinformation/{informationId}/")
    public JsonResult removeInformation(@PathVariable("informationId") String informationId);

    //查看通知
    @PostMapping("/pim/viewmyconsultation/{userId}/{guidanceStatus}/{pageNum}/")
    public JsonResult viewMyConsultation(@PathVariable("userId") Integer userId,@PathVariable("guidanceStatus") Integer guidanceStatus,@PathVariable("pageNum") Integer pageNum);

    //删除通知
    @PostMapping("/pim/delmyconsultation/{guidanceId}/")
    public JsonResult delConsultation(@PathVariable("userId") Integer guidanceId);

    //条件查询用户咨询
    @PostMapping("/pim/viewlikemyconsultation/{userId}/{guidanceStatus}/{like}/")
    public JsonResult viewLikeMyConsultation(@PathVariable("userId") Integer userId,@PathVariable("guidanceStatus") Integer guidanceStatus,@PathVariable("like") String like);
}
