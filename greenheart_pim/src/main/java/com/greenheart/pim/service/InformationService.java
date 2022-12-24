package com.greenheart.pim.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.greenheart.pim.pojo.Information;
import com.greenheart.pim.pojo.Picture;
import com.greenheart.pim.util.ObjectAndString;

import java.util.List;


public interface InformationService extends IService<Information> {
   default ObjectAndString<List<Information>,List<Picture>> myInformation(String userId,Integer informationStatus){return null;}

   default boolean removeInformation(String informationId){return false;}
}
