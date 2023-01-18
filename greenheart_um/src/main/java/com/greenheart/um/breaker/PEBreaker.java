package com.greenheart.um.breaker;

import com.greenheart.um.client.PEClient;
import entity.JsonResult;
import entity.StatusCode;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PEBreaker implements PEClient {
    @Override
    public JsonResult evaluating(Integer userId, String trialTitle, List<String> answers) {
        return new JsonResult(false, StatusCode.ERROR,"pe模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult viewScore(Integer userId, Integer pageNum) {
        return new JsonResult(false, StatusCode.ERROR,"pe模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult viewLikeScore(Integer userId, String like) {
        return new JsonResult(false, StatusCode.ERROR,"pe模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult delScore(Integer markId) {
        return new JsonResult(false, StatusCode.ERROR,"pe模块服务器出错，请联系管理员！");
    }
}
