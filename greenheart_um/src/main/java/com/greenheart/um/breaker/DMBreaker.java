package com.greenheart.um.breaker;

import com.greenheart.um.client.DMClient;
import com.greenheart.um.pojo.Trial;
import entity.JsonResult;
import entity.StatusCode;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DMBreaker implements DMClient {
    @Override
    public JsonResult allInformation(Integer pageNum) {
        return new JsonResult(false, StatusCode.ERROR,"dm模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult allLikeInformation(String like) {
        return new JsonResult(false, StatusCode.ERROR,"dm模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult findInformationBy(Integer informationId) {
        return new JsonResult(false, StatusCode.ERROR,"dm模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult selectAllTrial(Integer pageNum) {
        return new JsonResult(false, StatusCode.ERROR,"dm模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult selectLikeAllTrial(String like) {
        return new JsonResult(false, StatusCode.ERROR,"dm模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult startTrialByTitle(String trialTitle) {
        return new JsonResult(false, StatusCode.ERROR,"dm模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult selectTrialByTitle(String trialTitle, Integer pageNum) {
        return new JsonResult(false, StatusCode.ERROR,"dm模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult selectLikeTrialByTitle(String trialTitle, String like) {
        return new JsonResult(false, StatusCode.ERROR,"dm模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult selectTrialById(Integer trialId) {
        return new JsonResult(false, StatusCode.ERROR,"dm模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult addTrialOne(Integer trialNum,Trial trial) {
        return new JsonResult(false, StatusCode.ERROR,"dm模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult addTrial(Integer userId,Trial trial) {
        return new JsonResult(false, StatusCode.ERROR,"dm模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult removeTrialById(Integer trialId) {
        return new JsonResult(false, StatusCode.ERROR,"dm模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult removeTrial(String trialTitle) {
        return new JsonResult(false, StatusCode.ERROR,"dm模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult updateTrial(Trial trial) {
        return new JsonResult(false, StatusCode.ERROR,"dm模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult updateTrialCycle(Trial trial) {
        return new JsonResult(false, StatusCode.ERROR,"dm模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult updateAllTrial(List<Trial> trial) {
        return new JsonResult(false, StatusCode.ERROR,"dm模块服务器出错，请联系管理员！");
    }
}
