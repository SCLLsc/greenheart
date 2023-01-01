package com.greenheart.um.breaker;

import com.greenheart.um.client.UDClient;
import com.greenheart.um.pojo.Information;
import com.greenheart.um.util.ObjectAndString;
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
