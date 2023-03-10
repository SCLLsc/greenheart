package com.greenheart.collect.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.greenheart.collect.pojo.Collect;
import com.greenheart.collect.pojo.Information;
import com.greenheart.collect.util.ObjectAndString;

import java.util.List;


public interface CollectService extends IService<Collect> {
    default boolean addCollect(Integer userId,Integer informationId){return false;}

    default ObjectAndString<List<Information>,Integer> selectAllCollect(Integer userId,Integer pageNum){return null;}

    default boolean removeCollect(Integer userId,Integer informationId){return false;}

    default ObjectAndString<List<Information>, Integer> selectLikeAllCollect(Integer userId, String like){return null;}
}
