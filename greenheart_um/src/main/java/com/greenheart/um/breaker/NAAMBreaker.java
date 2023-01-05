package com.greenheart.um.breaker;

import com.greenheart.um.client.NAAMClient;
import com.mysql.cj.protocol.x.Notice;
import entity.JsonResult;
import entity.StatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

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
    public JsonResult addNotice(Notice notice) {
        return new JsonResult(false, StatusCode.ERROR,"naam模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult updateNotice(Notice notice) {
        return new JsonResult(false, StatusCode.ERROR,"naam模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult removeNotice(Integer noticeId) {
        return new JsonResult(false, StatusCode.ERROR,"naam模块服务器出错，请联系管理员！");
    }
}
