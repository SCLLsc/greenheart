package com.greenheart.user.breaker;

import com.greenheart.user.client.UMClient;
import entity.JsonResult;
import entity.StatusCode;
import org.springframework.stereotype.Component;

@Component
public class UMBreaker implements UMClient {
    @Override
    public JsonResult removeUser(Integer userId) {
        return new JsonResult(false, StatusCode.ERROR,"um模块服务器出错，请联系管理员！");
    }
}
