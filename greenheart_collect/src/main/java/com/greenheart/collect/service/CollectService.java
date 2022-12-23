package com.greenheart.collect.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.greenheart.collect.pojo.Collect;

import java.util.List;


public interface CollectService extends IService<Collect> {
    default boolean addCollect(Integer userId,Integer informationId){return false;}

    default List<Collect> selectAllCollect(Integer userId, Integer pageNum){return null;}
}
