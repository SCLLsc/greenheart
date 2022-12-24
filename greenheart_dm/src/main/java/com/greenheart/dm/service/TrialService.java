package com.greenheart.dm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.greenheart.dm.pojo.Trial;

import java.util.List;

public interface TrialService extends IService<Trial> {
    default List<Trial> selectAllTrial(){return null;}

    default boolean addTrial(List<Trial> trial){return false;}

    default boolean removeTrial(String trialTitle){return false;}

    default boolean updateTrial(Trial trial){return false;}

    default boolean updateTrialCycle(String trialTitle, Integer cycle){return false;}

    default  List<Trial> selectTrialByTitle(String trialTitle){return null;}

    //default boolean addTrialOne(Trial trial){return false;}
}
