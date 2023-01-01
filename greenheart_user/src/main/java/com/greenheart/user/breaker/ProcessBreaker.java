package com.greenheart.user.breaker;

import com.greenheart.user.client.ProcessClient;
import entity.JsonResult;
import entity.StatusCode;
import org.springframework.stereotype.Component;

@Component
public class ProcessBreaker implements ProcessClient {
    @Override
    public JsonResult allNoInformation(Integer pageNum) {
        return new JsonResult(false, StatusCode.ERROR,"process模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult process(Integer informationId, Integer process) {
        return new JsonResult(false, StatusCode.ERROR,"process模块服务器出错，请联系管理员！");
    }
}
