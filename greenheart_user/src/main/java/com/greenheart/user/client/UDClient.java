package com.greenheart.user.client;

import com.greenheart.user.breaker.UDBreaker;
import com.greenheart.user.pojo.Information;
import com.greenheart.user.util.ObjectAndString;
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
    @PostMapping("/ud/information/upload/{userId}/{informationType}/{informationTitle}/{informationContent}/")
    public JsonResult uploadInformation(@PathVariable Integer userId,@PathVariable String informationType,@PathVariable String informationTitle,@PathVariable String informationContent);
}
