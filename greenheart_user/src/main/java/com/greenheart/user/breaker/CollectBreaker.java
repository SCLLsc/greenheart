package com.greenheart.user.breaker;

import com.greenheart.user.client.CollectClient;
import entity.JsonResult;
import entity.StatusCode;
import org.springframework.stereotype.Component;


@Component
public class CollectBreaker implements CollectClient {

    //查看收藏
    @Override
    public JsonResult selectAllCollect(Integer userId,Integer pageNum){
        return new JsonResult(false, StatusCode.ERROR,"collect模块服务器出错，请联系管理员！");
    }

    //搜索查看收藏
    @Override
    public JsonResult selectLikeAllCollect(Integer userId, String like) {
        return new JsonResult(false, StatusCode.ERROR,"collect模块服务器出错，请联系管理员！");
    }

    //收藏资料
    @Override
    public JsonResult addCollect(Integer userId, Integer informationId) {
        return new JsonResult(false, StatusCode.ERROR,"collect模块服务器出错，请联系管理员！");
    }
    //取消收藏
    @Override
    public JsonResult removeById(Integer userId, Integer informationId) {
        return new JsonResult(false, StatusCode.ERROR,"collect模块服务器出错，请联系管理员！");
    }
}
