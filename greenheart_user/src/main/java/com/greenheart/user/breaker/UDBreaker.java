package com.greenheart.user.breaker;

import com.greenheart.user.client.UDClient;
import com.greenheart.user.pojo.Information;
import com.greenheart.user.util.ObjectAndString;
import entity.JsonResult;
import entity.StatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UDBreaker implements UDClient {
    @Override
    public JsonResult uploadInformation(Integer userId, String informationType, String informationTitle, String informationContent) {
        return new JsonResult(false, StatusCode.ERROR,"ud模块服务器出错，请联系管理员！");
    }
}
