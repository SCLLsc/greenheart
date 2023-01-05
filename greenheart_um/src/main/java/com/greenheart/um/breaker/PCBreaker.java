package com.greenheart.um.breaker;

import com.greenheart.um.client.PCClient;
import com.greenheart.um.pojo.Guidance;
import entity.JsonResult;
import entity.StatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class PCBreaker implements PCClient {
    @Override
    public JsonResult consult(Guidance guidance) {
        return new JsonResult(false, StatusCode.ERROR,"pc模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult viewReply(Integer guidanceId) {
        return new JsonResult(false, StatusCode.ERROR,"pc模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult viewConsultation(Integer userId, Integer guidanceStatus, Integer pageNum) {
        return new JsonResult(false, StatusCode.ERROR,"pc模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult viewLikeConsultation(Integer userId, Integer guidanceStatus, String like) {
        return new JsonResult(false, StatusCode.ERROR,"pc模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult removeGuidance(Integer guidanceId) {
        return new JsonResult(false, StatusCode.ERROR,"pc模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult delGuidance(Integer guidanceId) {
        return new JsonResult(false, StatusCode.ERROR,"pc模块服务器出错，请联系管理员！");
    }
}
