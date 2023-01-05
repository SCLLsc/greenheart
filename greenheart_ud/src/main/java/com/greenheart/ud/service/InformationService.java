package com.greenheart.ud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.greenheart.ud.pojo.Information;
import com.greenheart.ud.util.ObjectAndString;
import org.springframework.web.multipart.MultipartFile;

public interface InformationService extends IService<Information> {
    default String upload(ObjectAndString<Information, String> information, MultipartFile fileImage){return null;}

    default String uploadInformation(Information information){return null;}
}
