package com.greenheart.dm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.greenheart.dm.pojo.Information;
import com.greenheart.dm.util.ObjectAndString;

import java.util.List;


public interface InformationService extends IService<Information> {
    default ObjectAndString<List<Information>,Integer> allInformation(Integer pageNum) {
        return null;
    }

    default boolean removeInformation(String informationId) {
        return false;
    }

    default Information informationId(Integer informationId) {
        return null;
    }

    default ObjectAndString<List<Information>, Integer> allLikeInformation(String like) {
        return null;
    }
}