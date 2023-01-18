package com.greenheart.dm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.dm.dao.AnswerMapper;
import com.greenheart.dm.dao.TrialMapper;
import com.greenheart.dm.dao.UserMapper;
import com.greenheart.dm.pojo.Answer;
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
    private AnswerMapper answerMapper;

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
    public boolean addTrial(Integer userId,Trial trial){
         QueryWrapper qw=new QueryWrapper();
         qw.eq("trial_title",trial.getTrialTitle());
         List<Trial> trialList=trialMapper.selectList(qw);
         if(trialList.size()!=0){
             return false;
         }else {
             trial.setUserId(userId);
             trial.setTrialContent("无");
             trial.setTrialAnswer("无");
             trial.setCreationTime(new Date());
             trial.setTrialScore(0);
             trialMapper.insert(trial);
             return true;

         }
    }
    //修改Execl心理评测
    public boolean updateAllTrial(List<Trial> trial){
        System.out.println("myexcel:"+trial);
        QueryWrapper qw=new QueryWrapper();
        qw.eq("trial_title",trial.get(0).getTrialTitle());
        List<Trial> trialList=trialMapper.selectList(qw);
        for(int i=0;i<trial.size();i++){
            Trial trial1=trialList.get(i);
            trial1.setTrialContent(trial.get(i).getTrialContent());
//            trial1.setTrialAnswer(trial.get(i).getTrialAnswer());
            trial1.setTrialScore(trial.get(i).getTrialScore());
            trial1.setCycle(trial.get(i).getCycle());
            trialMapper.updateById(trial1);
        }
         return true;
    }
    //增加Execl心理评测
    public boolean addExcelAllTrial(Integer userId,List<Trial> trial){
        System.out.println("myexcel:"+trial);
        QueryWrapper qw=new QueryWrapper();
        qw.eq("trial_title",trial.get(0).getTrialTitle());
        List<Trial> trialList=trialMapper.selectList(qw);
        Date date=new Date();
        if(trialList.size()!=0){
            return false;
        }else {
            for (int i = 0; i < trial.size(); i++) {
                Trial trial1 = new Trial();
                trial1.setTrialType(trial.get(i).getTrialType());
                trial1.setTrialTitle(trial.get(i).getTrialTitle());
                trial1.setTrialContent(trial.get(i).getTrialContent());
                trial1.setTrialAnswer("false");
                trial1.setTrialScore(trial.get(i).getTrialScore());
                trial1.setCycle(trial.get(i).getCycle());
                trial1.setUserId(userId);
                trial1.setCreationTime(date);
                trialMapper.insert(trial1);
            }
            return true;
        }
    }
    //增加Execl心理评测的答案
    public boolean addAllAnswer(String trialTitle,List<Answer> answer){
        System.out.println("myexcelanswer:"+answer);
        QueryWrapper qw=new QueryWrapper();
        qw.eq("trial_title",trialTitle);
        List<Trial> trialList=trialMapper.selectList(qw);
        System.out.println("所有的测试题目"+trialList);

        if(trialList.size()==0){
            return false;
        }else {
            if(trialList.get(0).getTrialAnswer().equals("false")){

                for(Trial trial:trialList){
                    trial.setTrialAnswer("true");
                    trialMapper.updateById(trial);
                }

                for (int i = 0; i < answer.size(); i++) {
                    Answer answer1 = new Answer();
                    answer1.setTrialId(trialList.get(i).getTrialId());
                    answer1.setTrialContent(answer.get(i).getTrialContent());
                    answer1.setAnswerA(answer.get(i).getAnswerA());
                    answer1.setAnswerB(answer.get(i).getAnswerB());
                    answer1.setAnswerC(answer.get(i).getAnswerC());
                    answer1.setAnswerD(answer.get(i).getAnswerD());
                    answer1.setScoreA(answer.get(i).getScoreA());
                    answer1.setScoreB(answer.get(i).getScoreB());
                    answer1.setScoreC(answer.get(i).getScoreC());
                    answer1.setScoreD(answer.get(i).getScoreD());
                    answerMapper.insert(answer1);
                }
                return true;
            }else {

                return false;
            }
        }
    }
    //查找单个答案
    public Answer findAnswerById(Integer trialId){
        QueryWrapper qw=new QueryWrapper();
        qw.eq("trial_id",trialId);
        Answer answer=answerMapper.selectOne(qw);
        return answer;
    }
    //查找所有答案
    public List<Answer> findAllAnswer(String trialTitle){
        QueryWrapper qw=new QueryWrapper();
        qw.eq("trial_title",trialTitle);
        List<Trial> trials=trialMapper.selectList(qw);
        System.out.println("所有的测试题目"+trials);
        List<Answer> answers=new ArrayList<>();
        for(Trial trial:trials){
            QueryWrapper qw1=new QueryWrapper();
            qw1.eq("trial_id",trial.getTrialId());
            answers.add(answerMapper.selectOne(qw1));
        }
         return answers;
    }
    //增加单个心理评测
    public boolean addTrialOne(Integer trialNum,Trial trial){
         Date date=new Date();
         for (int i=0;i<trialNum;i++){
             Trial trial1=new Trial();
             trial1.setTrialType(trial.getTrialType());
             trial1.setTrialTitle(trial.getTrialTitle());
             trial1.setTrialContent(trial.getTrialContent());
             trial1.setTrialAnswer(trial.getTrialAnswer());
             trial1.setCycle(trial.getCycle());
             trial1.setTrialScore(trial.getTrialScore());
             trial1.setUserId(trial.getUserId());
             trial1.setCreationTime(date);
             trialMapper.insert(trial1);
         }
            return true;

    }
    //删除单个心理评测
    public boolean removeOneById(Integer trialId){
        QueryWrapper qw=new QueryWrapper();
        qw.eq("trial_id",trialId);
        int r1=trialMapper.deleteById(trialId);
        int r2=answerMapper.delete(qw);
        if(r1!=0&&r2!=0){
            return true;
        }else{
            return false;
        }
    }
    //删除心理评测
    public boolean removeTrial(String trialTitle){
         QueryWrapper qw=new QueryWrapper();
         qw.eq("trial_title",trialTitle);
         List<Trial> trials=trialMapper.selectList(qw);
         if(trials.get(0).getTrialAnswer().equals("false")){
             for(Trial trial:trials){
                 trialMapper.deleteById(trial.getTrialId());
             }
         }else {
             for(Trial trial:trials){
                 QueryWrapper qw1=new QueryWrapper();
                 qw.eq("trial_id",trial.getTrialId());
                 answerMapper.delete(qw1);
             }
             for(Trial trial:trials){
                 trialMapper.deleteById(trial.getTrialId());
             }
         }
        List<Trial> trialList=trialMapper.selectList(qw);
        if(trialList.size()==0){
            return true;
        }else{
            return false;
        }
    }
    //修改心理评测
    public boolean updateTrial(Trial trial){
      QueryWrapper qw=new QueryWrapper();
      qw.eq("trial_id",trial.getTrialId());
      Answer answer=answerMapper.selectOne(qw);
      if(answer!=null){
          answer.setTrialContent(trial.getTrialContent());
          int r1=answerMapper.updateById(answer);
          Trial oldTrial=trialMapper.selectById(trial.getTrialId());
          oldTrial.setTrialAnswer(trial.getTrialAnswer());
          oldTrial.setTrialContent(trial.getTrialContent());
          oldTrial.setTrialScore(trial.getTrialScore());
          int result=trialMapper.updateById(oldTrial);
          if(r1!=0&&result!=0){
              return true;
          }else{
              return false;
          }
      }else {
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

    }
    //修改评测时间
    public boolean updateTrialCycle(Trial trial){
         QueryWrapper qw=new QueryWrapper();
         qw.eq("trial_title",trial.getTrialTitle());
         List<Trial> trials=trialMapper.selectList(qw);
         System.out.println(trials);
         int result=0;
         for (Trial trial1:trials){
             trial1.setCycle(trial.getCycle());
             result+=trialMapper.updateById(trial1);
         }
        System.out.println(result);
         if(result!=0){
             return true;
         }else{
             return false;
         }
    }
    //修改答案
    public boolean updateAnswer(Answer answer){
        System.out.println(answer);
        int result=answerMapper.updateById(answer);
        if(result!=0){
            return true;
        }else{
            return false;
        }
    }
    //修改所有答案
    public boolean updateAllAnswer(String trialTitle, List<Answer> answer){
        QueryWrapper qw=new QueryWrapper();
        qw.eq("trial_title",trialTitle);
        List<Trial> trialList=trialMapper.selectList(qw);
        if(trialList.get(0).getTrialAnswer().equals("true")){
            for(int i=0;i<trialList.size();i++){
                QueryWrapper qw1=new QueryWrapper();
                qw1.eq("trial_id",trialList.get(i).getTrialId());
                Answer answer1=answerMapper.selectOne(qw1);
                answer1.setTrialContent(answer.get(i).getTrialContent());
                answer1.setAnswerA(answer.get(i).getAnswerA());
                answer1.setAnswerB(answer.get(i).getAnswerB());
                answer1.setAnswerC(answer.get(i).getAnswerC());
                answer1.setAnswerD(answer.get(i).getAnswerD());
                answer1.setScoreA(answer.get(i).getScoreA());
                answer1.setScoreB(answer.get(i).getScoreB());
                answer1.setScoreC(answer.get(i).getScoreC());
                answer1.setScoreD(answer.get(i).getScoreD());
                answerMapper.updateById(answer1);
            }
            return true;
        }else {
            return false;
        }

    }

}
