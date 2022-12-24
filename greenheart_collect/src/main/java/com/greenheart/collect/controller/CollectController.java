package com.greenheart.collect.controller;

import com.greenheart.collect.pojo.Collect;
import com.greenheart.collect.pojo.Information;
import com.greenheart.collect.pojo.Picture;
import com.greenheart.collect.service.CollectService;
import com.greenheart.collect.util.ObjectAndString;
import entity.JsonResult;
import entity.StatusCode;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@Setter(onMethod_={@Autowired} )
public class CollectController {
    private CollectService collectService;

    //查看收藏
    @PostMapping("/collect/{userId}/")
    public JsonResult selectAllCollect(@PathVariable Integer userId){
        ObjectAndString<List<Information>,List<Picture>> collects= collectService.selectAllCollect(userId);
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",collects);
    }
    //收藏资料
    @PostMapping("/collect/addcollect/{userId}/{informationId}/")
    public JsonResult addCollect(@PathVariable Integer userId,@PathVariable Integer informationId){
        if(collectService.addCollect(userId, informationId)){
            return new JsonResult(true, StatusCode.SUCESS,"收藏成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"收藏失败");
        }
    }
    //取消收藏
    @PostMapping("/collect/removecollect/{userId}/{informationId}/")
    public JsonResult removeById(@PathVariable Integer userId,@PathVariable Integer informationId){
        boolean flag=collectService.removeCollect(userId, informationId);
        if(flag){
            return new JsonResult(true, StatusCode.SUCESS,"取消成功");
        }else{
            return new JsonResult(false, StatusCode.ERROR,"取消失败");
        }
    }
}
