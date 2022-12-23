package com.greenheart.um.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.um.dao.*;
import com.greenheart.um.pojo.Collect;
import com.greenheart.um.pojo.Guidance;
import com.greenheart.um.pojo.Information;
import com.greenheart.um.pojo.Reply;
import com.greenheart.um.service.ReplyService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Setter(onMethod_ ={@Resource} )
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements ReplyService {
    private UserMapper userMapper;
    private CollectMapper collectMapper;
    private GuidanceMapper guidanceMapper;
    private InformationMapper informationMapper;
    private MarkMapper markMapper;
    private PictureMapper pictureMapper;
    private ReplyMapper replyMapper;

    //查看咨询
    public List<Guidance> viewConsultation(Integer guidanceStatus,Integer pageNum){
        QueryWrapper qw=new QueryWrapper();
        qw.eq("guidance_status", guidanceStatus);
        Page<Guidance> page=new Page<>(pageNum,3);
        List<Guidance> guidances=guidanceMapper.selectPage(page,qw).getRecords();
        long total=page.getTotal();
        long current=page.getCurrent();
        long pages=page.getPages();
        return guidances;
    }
    //查看单个咨询未回复内容
    public Guidance viewOneConsultation(Integer guidanceId){
        Guidance guidance=guidanceMapper.selectById(guidanceId);
        return guidance;
    }
    //心理咨询回复
    public boolean pcReply(Reply pcReply){
        QueryWrapper qw=new QueryWrapper();
        qw.eq("guidance_id",pcReply.getGuidanceId());
        Guidance guidance=guidanceMapper.selectOne(qw);
        guidance.setGuidanceStatus(2);
        int r1=guidanceMapper.updateById(guidance);
        Reply reply=new Reply();
        reply.setGuidanceId(pcReply.getGuidanceId());
        reply.setReplyContent(pcReply.getReplyContent());
        reply.setReplyDate(new Date());
        int r2=replyMapper.insert(reply);
        if(r1!=0&&r2!=0){
            return true;
        }else {
            return false;
        }
    }

    //删除用户有关的所有信息
    public boolean cancellation(Integer userId){
        QueryWrapper qw=new QueryWrapper();
        qw.eq("user_id",userId);
        List<Information> informations=informationMapper.selectList(qw);//自己上传的资料
        System.out.println(informations);
        List<Integer> iIds=new ArrayList();//资料的Id
        for (Information information :informations){
            iIds.add(information.getInformationId());
        }
        System.out.println(iIds);
        //根据Id删除上传的资料、资料相关的图片、和收藏记录
        for (Integer informationId:iIds){
            QueryWrapper qw1=new QueryWrapper();
            qw1.eq("information_id",informationId);
            List<Collect> collects=collectMapper.selectList(qw1);
            System.out.println(collects);
            if(collects!=null){
                List<Integer> cIds=new ArrayList<Integer>();
                for(Collect collect :collects ){
                    cIds.add(collect.getCollectId());
                }
                QueryWrapper qw2=new QueryWrapper();
                qw2.eq("information_id",informationId);
                int r11=collectMapper.deleteBatchIds(cIds);
                int r22=informationMapper.deleteById(informationId);
                int r33=pictureMapper.delete(qw2);
            }else {
                int r11=informationMapper.deleteById(informationId);
                QueryWrapper qw2=new QueryWrapper();
                qw2.eq("information_id",informationId);
                int r33=pictureMapper.delete(qw2);
            }
        }
        int r1=collectMapper.delete(qw);//删除自己收藏的
        int r3=markMapper.delete(qw);//删除测试成绩
        int r4=pictureMapper.delete(qw);//删除头像
        //查找所有的咨询
        List<Guidance> guidanceList=guidanceMapper.selectList(qw);
        System.out.println(guidanceList);
        //删除咨询的回复
        for(Guidance guidance : guidanceList){
            QueryWrapper qww=new QueryWrapper();
            Integer guidanceId=guidance.getGuidanceId();
            qww.eq("guidance_id",guidanceId);
            replyMapper.delete(qww);
        }
        int r5=guidanceMapper.delete(qw);//删除咨询
        int r6=userMapper.deleteById(userId);//删除用户
        return true;
    }
}
