package com.greenheart.dm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.dm.dao.TrialMapper;
import com.greenheart.dm.dao.UserMapper;
import com.greenheart.dm.pojo.Trial;
import com.greenheart.dm.service.TrialService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
@Setter(onMethod_ ={@Resource})
public class TrialServiceImpl extends ServiceImpl<TrialMapper, Trial> implements TrialService {
    private UserMapper userMapper;
    private TrialMapper trialMapper;

    //查看心理评测
     public List<Trial> selectAllTrial(Integer pageNum){
         Page<Trial> page=new Page<>(pageNum,10);
         QueryWrapper<Trial> qw=new QueryWrapper();
         qw.groupBy("trial_title");
         trialMapper.selectPage(page,qw);
         List<Trial> trials=page.getRecords();
         return trials;
     }
    //查看评测内题目
    public List<Trial> selectTrialByTitle(String trialTitle){
        QueryWrapper<Trial> qw=new QueryWrapper();
        qw.eq("trial_title",trialTitle);
        List<Trial> trials=trialMapper.selectList(qw);
        return trials;
    }
    //增加心理评测
    public boolean addTrial(List<Trial> trial){
         for(Trial trial1:trial){
            trial1.setCreationTime(new Date());
        }
        if(saveBatch(trial)){
            return true;
        }else{
            return false;
         }
    }
    //删除心理评测
    public boolean removeTrial(String trialTitle){
         QueryWrapper qw=new QueryWrapper();
         qw.eq("trial_title",trialTitle);
         int result=trialMapper.delete(qw);
        if(result!=0){
            return true;
        }else{
            return false;
        }
    }
    //修改心理评测
    public boolean updateTrial(Trial trial){
      Trial oldTrial=trialMapper.selectById(trial.getTrialId());
      oldTrial.setTrialAnswer(trial.getTrialAnswer());
      oldTrial.setTrialContent(trial.getTrialContent());
      oldTrial.setTrialScore(trial.getTrialScore());
      int result=trialMapper.updateById(oldTrial);
        if(result!=0){
            return true;
        }else{
            return false;
        }
    }
    //修改评测时间
    public boolean updateTrialCycle(String trialTitle, Integer cycle){
         QueryWrapper qw=new QueryWrapper();
         qw.eq("trial_title",trialTitle);
         List<Trial> trials=trialMapper.selectList(qw);
         System.out.println(trials);
         int result=0;
         for (Trial trial:trials){
             trial.setCycle(cycle);
             result+=trialMapper.updateById(trial);
         }
        System.out.println(result);
         if(result!=0){
             return true;
         }else{
             return false;
         }
    }

}
