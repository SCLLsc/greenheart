package com.greenheart.user.breaker;

import com.greenheart.user.client.PIMClient;
import com.greenheart.user.pojo.User;
import entity.JsonResult;
import entity.StatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class PIMBreaker implements PIMClient {
    @Override
    public JsonResult myself(String userId) {
        return new JsonResult(false, StatusCode.ERROR,"pim模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult updateMyself(User user) {
        return new JsonResult(false, StatusCode.ERROR,"pim模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult updateMyPwd(User user) {
        return new JsonResult(false, StatusCode.ERROR,"pim模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult myInformation(String userId, Integer informationStatus, Integer pageNum) {
        return new JsonResult(false, StatusCode.ERROR,"pim模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult myLikeInformation(String userId, Integer informationStatus, String like) {
        return new JsonResult(false, StatusCode.ERROR,"pim模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult removeInformation(String informationId) {
        return new JsonResult(false, StatusCode.ERROR,"pim模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult viewMyConsultation(Integer userId, Integer guidanceStatus, Integer pageNum) {
        return new JsonResult(false, StatusCode.ERROR,"pim模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult delConsultation(Integer guidanceId) {
        return new JsonResult(false, StatusCode.ERROR,"pim模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult viewLikeMyConsultation(Integer userId, Integer guidanceStatus, String like) {
        return new JsonResult(false, StatusCode.ERROR,"pim模块服务器出错，请联系管理员！");
    }
}
