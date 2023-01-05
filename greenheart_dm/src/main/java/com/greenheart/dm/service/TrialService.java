package com.greenheart.dm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.greenheart.dm.pojo.Trial;
import com.greenheart.dm.util.ObjectAndString;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TrialService extends IService<Trial> {
    default ObjectAndString<List<Trial>,Integer> selectAllTrial(Integer pageNum){return null;}

    default boolean addTrial(Trial trial){return false;}

    default boolean removeTrial(String trialTitle){return false;}

    default boolean updateTrial(Trial trial){return false;}

    default boolean updateTrialCycle(Trial trial){return false;}

    default ObjectAndString<List<Trial>,Integer> selectTrialByTitle(String trialTitle, Integer pageNum){return null;}

    default boolean addTrialOne(Trial trial){return false;}

    default ObjectAndString<List<Trial>, Integer> selectLikeAllTrial(String like){return null;}

    default ObjectAndString<List<Trial>, Integer> selectLikeTrialByTitle(String trialTitle, String like){return null;}

    default List<Trial> startTrialByTitle(String trialTitle){return null;}
}
