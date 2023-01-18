package com.greenheart.dm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.greenheart.dm.pojo.Answer;
import com.greenheart.dm.pojo.Trial;
import com.greenheart.dm.util.ObjectAndString;

import java.util.List;

public interface TrialService extends IService<Trial> {
    default ObjectAndString<List<Trial>,Integer> selectAllTrial(Integer pageNum){return null;}

    default boolean addTrial(Integer userId,Trial trial){return false;}

    default boolean removeTrial(String trialTitle){return false;}

    default boolean updateTrial(Trial trial){return false;}

    default boolean updateTrialCycle(Trial trial){return false;}

    default ObjectAndString<List<Trial>,Integer> selectTrialByTitle(String trialTitle, Integer pageNum){return null;}

    default boolean addTrialOne(Integer trialNum,Trial trial){return false;}

    default ObjectAndString<List<Trial>, Integer> selectLikeAllTrial(String like){return null;}

    default ObjectAndString<List<Trial>, Integer> selectLikeTrialByTitle(String trialTitle, String like){return null;}

    default List<Trial> startTrialByTitle(String trialTitle){return null;}

    default boolean updateAllTrial(List<Trial> trial){return false;}

    default boolean addExcelAllTrial(Integer userId,List<Trial> trial){return false;}

    default boolean addAllAnswer(String trialTitle, List<Answer> answer){return false;}

    default boolean removeOneById(Integer trialId){return false;}

    default Answer findAnswerById(Integer trialId){return null;}

    default List<Answer> findAllAnswer(String trialTitle){return null;}

    default boolean updateAnswer(Answer answer){return false;}

    default boolean updateAllAnswer(String trialTitle, List<Answer> answer){return false;}
}
