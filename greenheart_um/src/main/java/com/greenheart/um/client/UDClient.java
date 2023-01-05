package com.greenheart.um.client;

import com.greenheart.um.breaker.UDBreaker;
import com.greenheart.um.pojo.Information;
import com.greenheart.um.util.ObjectAndString;
import entity.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "greenheartud",fallback = UDBreaker.class)
public interface UDClient {
    //上传资料
    @PostMapping("/ud/information/upload/")
    public JsonResult uploadInformation(@RequestBody Information information);
}
