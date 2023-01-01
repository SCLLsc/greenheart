package com.greenheart.um.breaker;

import com.greenheart.um.client.UserClient;
import entity.JsonResult;
import entity.StatusCode;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class UserBreaker implements UserClient {

    @Override
    public JsonResult login(String email, String userPwd, HttpSession session) {
        return new JsonResult(false, StatusCode.ERROR,"user模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult logout(HttpSession session) {
        return new JsonResult(false, StatusCode.ERROR,"user模块服务器出错，请联系管理员！");
    }
}
