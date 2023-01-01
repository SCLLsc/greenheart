package com.greenheart.um.breaker;

import com.greenheart.um.client.PIMClient;
import entity.JsonResult;
import entity.StatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class PIMBreaker implements PIMClient {
    @Override
    public JsonResult myself(String userId) {

        return new JsonResult(false, StatusCode.ERROR,"pim模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult updateMyself(Integer userId, String userName, String email) {
        return new JsonResult(false, StatusCode.ERROR,"pim模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult updateMyPwd(Integer userId, String userPwd) {
        return new JsonResult(false, StatusCode.ERROR,"pim模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult myInformation(String userId, Integer informationStatus, Integer pageNum) {
        return new JsonResult(false, StatusCode.ERROR,"pim模块服务器出错，请联系管理员！");
    }

    @Override
    public JsonResult removeInformation(String informationId) {
        return new JsonResult(false, StatusCode.ERROR,"pim模块服务器出错，请联系管理员！");
    }
}
