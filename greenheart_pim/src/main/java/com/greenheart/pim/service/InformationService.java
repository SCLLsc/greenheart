package com.greenheart.pim.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.greenheart.pim.pojo.Information;
import com.greenheart.pim.util.ObjectAndString;

import java.util.List;


public interface InformationService extends IService<Information> {
   default ObjectAndString<List<Information>,Integer> myInformation(String userId,Integer informationStatus,Integer pageNum){return null;}

   default boolean removeInformation(Integer informationId){return false;}

   default ObjectAndString<List<Information>, Integer> myLikeInformation(String userId, Integer informationStatus, String like){return null;}
}
