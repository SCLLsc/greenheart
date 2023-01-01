package com.greenheart.dm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.dm.dao.TrialMapper;
import com.greenheart.dm.dao.UserMapper;
import com.greenheart.dm.pojo.Information;
import com.greenheart.dm.pojo.Trial;
import com.greenheart.dm.service.TrialService;
import com.greenheart.dm.util.ObjectAndString;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
@Setter(onMethod_ ={@Resource})
public class TrialServiceImpl extends ServiceImpl<TrialMapper, Trial> implements TrialService {
    private UserMapper userMapper;
    private TrialMapper trialMapper;

    //查看心理评测
     public ObjectAndString<List<Trial>,Integer> selectAllTrial(Integer pageNum){
         ObjectAndString<List<Trial>,Integer> trial=new ObjectAndString<>();
         Page<Trial> page=new Page<>(pageNum,3);
         QueryWrapper<Trial> qw=new QueryWrapper();
         qw.groupBy("trial_title");
         trialMapper.selectPage(page,qw);
         List<Trial> trials=page.getRecords();
         Integer total=(int)page.getTotal();
         System.out.println("111111"+trials.get(0).getTrialTitle().contains("b"));
         trial.setFirst(trials);
         trial.setSecond(total);
         return trial;
     }
     //搜索查看心理评测
    public ObjectAndString<List<Trial>, Integer> selectLikeAllTrial(String like){
        ObjectAndString<List<Trial>,Integer> trial=new ObjectAndString<>();
        QueryWrapper<Trial> qw=new QueryWrapper();
        qw.groupBy("trial_title");
        List<Trial> trials=trialMapper.selectList(qw);
        List<Trial> trialList=new ArrayList<>();
        for(Trial trial1:trials){
            if(trial1.getTrialTitle().contains(String.valueOf(like))){
                trialList.add(trial1);
            }
        }
        System.out.println("111111"+trials.get(0).getTrialTitle().contains(like));
        System.out.println("222222"+trialList);
        Integer total=trialList.size();
        trial.setFirst(trialList);
        trial.setSecond(total);
        return trial;
    }
    //查看评测内题目
    public ObjectAndString<List<Trial>,Integer> selectTrialByTitle(String trialTitle,Integer pageNum){
        ObjectAndString<List<Trial>,Integer> trial=new ObjectAndString<>();
        Page<Trial> page=new Page<>(pageNum,3);
        QueryWrapper qw=new QueryWrapper();
        qw.eq("trial_title",trialTitle);
        trialMapper.selectPage(page,qw);
        List<Trial> trials=page.getRecords();
        Integer total=(int) page.getTotal();
        trial.setFirst(trials);
        trial.setSecond(total);
        System.out.println(trials);
        return trial;
    }
    //开始做心理评测试题
    public List<Trial> startTrialByTitle(String trialTitle){
        QueryWrapper qw=new QueryWrapper();
        qw.eq("trial_title",trialTitle);
        List<Trial> trials=trialMapper.selectList(qw);
        System.out.println(trials);
        return trials;
    }
    //搜索查看评测内题目
    public ObjectAndString<List<Trial>,Integer> selectLikeTrialByTitle(String trialTitle,String like){
        ObjectAndString<List<Trial>,Integer> trial=new ObjectAndString<>();
        QueryWrapper qw=new QueryWrapper();
        qw.eq("trial_title",trialTitle);
        List<Trial> trials=trialMapper.selectList(qw);
        List<Trial> trialList=new ArrayList<>();
        for(Trial trial1:trials){
            if(trial1.getTrialContent().contains(String.valueOf(like))){
                trialList.add(trial1);
            }
        }
        Integer total=trialList.size();
        trial.setFirst(trialList);
        trial.setSecond(total);
        System.out.println(trials);
        return trial;
    }
    //增加心理评测
    public boolean addTrial(Integer userId,String trialType, String trialTitle, Integer cycle){
         Trial trial=new Trial();
         trial.setTrialType(trialType);
         trial.setTrialTitle(trialTitle);
         trial.setCycle(cycle);
         trial.setTrialContent("无");
         trial.setTrialAnswer("无");
         trial.setCreationTime(new Date());
         trial.setTrialScore(0);
         trial.setUserId(userId);
        if(trialMapper.insert(trial)==1){
            return true;
        }else{
            return false;
         }
    }
    //增加单个心理评测
    public boolean addTrialOne(Integer userId,String trialType,String trialTitle, String trialContent, String trialAnswer,Integer trialScore,Integer cycle){
         Trial trial=new Trial();
         trial.setUserId(userId);
         trial.setTrialTitle(trialTitle);
         trial.setTrialScore(trialScore);
         trial.setTrialAnswer(trialAnswer);
         trial.setTrialContent(trialContent);
         trial.setTrialType(trialType);
         trial.setCreationTime(new Date());
         trial.setCycle(cycle);
        int result=trialMapper.insert(trial);
        if(result!=0){
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
    public boolean updateTrial(Integer trialId, String trialContent, String trialAnswer,Integer trialScore){
      Trial oldTrial=trialMapper.selectById(trialId);
      oldTrial.setTrialAnswer(trialAnswer);
      oldTrial.setTrialContent(trialContent);
      oldTrial.setTrialScore(trialScore);
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
