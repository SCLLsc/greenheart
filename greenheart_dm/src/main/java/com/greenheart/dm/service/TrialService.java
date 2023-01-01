package com.greenheart.dm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.greenheart.dm.pojo.Trial;
import com.greenheart.dm.util.ObjectAndString;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TrialService extends IService<Trial> {
    default ObjectAndString<List<Trial>,Integer> selectAllTrial(Integer pageNum){return null;}

    default boolean addTrial(Integer userId,String trialType, String trialTitle, Integer cycle){return false;}

    default boolean removeTrial(String trialTitle){return false;}

    default boolean updateTrial(Integer trialId, String trialContent, String trialAnswer,Integer trialScore){return false;}

    default boolean updateTrialCycle(String trialTitle, Integer cycle){return false;}

    default ObjectAndString<List<Trial>,Integer> selectTrialByTitle(String trialTitle, Integer pageNum){return null;}

    default boolean addTrialOne(Integer userId,String trialType,String trialTitle, String trialContent, String trialAnswer,Integer trialScore,Integer cycle){return false;}

    default ObjectAndString<List<Trial>, Integer> selectLikeAllTrial(String like){return null;}

    default ObjectAndString<List<Trial>, Integer> selectLikeTrialByTitle(String trialTitle, String like){return null;}

    default List<Trial> startTrialByTitle(String trialTitle){return null;}
}
