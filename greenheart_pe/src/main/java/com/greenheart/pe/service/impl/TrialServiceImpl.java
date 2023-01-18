package com.greenheart.pe.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.pe.dao.AnswerMapper;
import com.greenheart.pe.dao.MarkMapper;
import com.greenheart.pe.dao.TrialMapper;
import com.greenheart.pe.pojo.Answer;
import com.greenheart.pe.pojo.Mark;
import com.greenheart.pe.pojo.Trial;
import com.greenheart.pe.service.TrialService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Setter(onMethod_ ={@Resource})
public class TrialServiceImpl extends ServiceImpl<TrialMapper, Trial> implements TrialService {
    private MarkMapper markMapper;
    private TrialMapper trialMapper;
    private AnswerMapper answerMapper;

    //心理评测
    public boolean evaluating(Integer userId, String trialTitle, List<String[]> answers){
        System.out.println("答案"+ answers.get(0)[0]);
        System.out.println("答案"+ Arrays.toString(answers.get(0)));
        int result=0;
        QueryWrapper qw=new QueryWrapper();
        qw.eq("trial_title",trialTitle);
        List<Trial> trials=trialMapper.selectList(qw);
        for(int i=0;i<trials.size();i++){
            QueryWrapper qw1=new QueryWrapper();
            qw1.eq("trial_id",trials.get(i).getTrialId());
            Answer answer=answerMapper.selectOne(qw1);
            System.out.println(Arrays.toString(answers.get(i)).equals(answer.getAnswerA()));
            System.out.println(answers.get(i)[0].equals(answer.getAnswerA()));
            if(answers.get(i)[0].equals(answer.getAnswerA())){
                result=result+answer.getScoreA();
            }else if(answers.get(i)[0].equals(answer.getAnswerB())){
                result=result+answer.getScoreB();
            }else if(answers.get(i)[0].equals(answer.getAnswerC())){
                result=result+answer.getScoreC();
            }else if(answers.get(i)[0].equals(answer.getAnswerD())){
                result=result+answer.getScoreD();
            }
        }
//        List<Answer> answers1=answerMapper.selectList(qw);
//        for(int i=0;i<answers.size();i++){
//            if(answers.get(i).equals(trials.get(i).getTrialAnswer())){
//                result+=trials.get(i).getTrialScore();
//            }
//        }
        System.out.println(result);
        Mark mark=new Mark();
        mark.setMarkDate(new Date());
        mark.setMarkScore(result);
        mark.setTrialTitle(trialTitle);
        mark.setUserId(userId);
        int r1=markMapper.insert(mark);
        if(r1!=0){
            return true;
        }else {
            return false;
        }
    }

}
