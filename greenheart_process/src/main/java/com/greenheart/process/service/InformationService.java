package com.greenheart.process.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.greenheart.process.pojo.Information;
import com.greenheart.process.pojo.Picture;
import com.greenheart.process.util.ObjectAndString;

import java.util.List;


public interface InformationService extends IService<Information> {
    default ObjectAndString<List<Information>,List<Picture>> allNoInformation(Integer pageNum){return null;}

    default boolean process(Integer informationId, Integer process){return false;}
}
