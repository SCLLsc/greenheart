package com.greenheart.pe.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.greenheart.pe.pojo.Trial;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrialService extends IService<Trial> {
    default boolean evaluating(Integer userId,String trialTitle,Integer markScore){return false;}
}
