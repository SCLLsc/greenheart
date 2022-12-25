package com.greenheart.pe.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.pe.dao.MarkMapper;
import com.greenheart.pe.dao.TrialMapper;
import com.greenheart.pe.pojo.Mark;
import com.greenheart.pe.pojo.Trial;
import com.greenheart.pe.service.TrialService;
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
    private MarkMapper markMapper;
    private TrialMapper trialMapper;

    //心理评测
    public boolean evaluating(Integer userId,String trialTitle,Integer markScore){
//        Integer result=0;
//        QueryWrapper qw=new QueryWrapper();
//        qw.eq("trial_title",trialTitle);
//        List<Trial> trials=trialMapper.selectList(qw);
//        for(int i=0;i<answers.size();i++){
//            if(answers.get(i).equals(trials.get(i).getTrialAnswer())){
//                result+=trials.get(i).getTrialScore();
//            }
//        }
        Mark mark=new Mark();
        mark.setMarkDate(new Date());
        mark.setMarkScore(markScore);
        mark.setTrialTitle(trialTitle);
        mark.setUserId(userId);
        int result=markMapper.insert(mark);
        if(result!=0){
            return true;
        }else {
            return false;
        }
    }

}
