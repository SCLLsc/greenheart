package com.greenheart.um.breaker;

import com.greenheart.um.client.NAAMClient;
import entity.JsonResult;
import entity.StatusCode;
import org.springframework.stereotype.Component;

@Component
public class NAAMBreaker implements NAAMClient {
    @Override
    public JsonResult allNotice() {
        return new JsonResult(false, StatusCode.ERROR,"naam模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult oneNotice(Integer noticeId) {
        return new JsonResult(false, StatusCode.ERROR,"naam模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult addNotice(String noticeTitle, String noticeContent) {
        return new JsonResult(false, StatusCode.ERROR,"naam模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult updateNotice(Integer noticeId, String noticeTitle, String noticeContent) {
        return new JsonResult(false, StatusCode.ERROR,"naam模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult removeNotice(Integer noticeId) {
        return new JsonResult(false, StatusCode.ERROR,"naam模块服务器出错，请联系管理员！");
    }
}
