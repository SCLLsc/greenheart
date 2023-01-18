package com.greenheart.um.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.um.dao.*;
import com.greenheart.um.pojo.*;
import com.greenheart.um.service.ReplyService;
import com.greenheart.um.util.ObjectAndString;
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
    private ReplyMapper replyMapper;

    //查看所有用户
    public ObjectAndString<List<User>,Integer> viewAllUser(Integer pageNum){
        ObjectAndString<List<User>,Integer> user=new ObjectAndString<>();
        Page<User> page=new Page<>(pageNum,3);
        QueryWrapper qw=new QueryWrapper();
        qw.ne("role",1);
        userMapper.selectPage(page,qw);
        List<User> users=page.getRecords();
        Integer total=(int)page.getTotal();
        System.out.println(total);
        user.setFirst(users);
        user.setSecond(total);
        return user;
    }
    //查看所有管理员
    public ObjectAndString<List<User>,Integer> viewMdAllUser(Integer pageNum){
        ObjectAndString<List<User>,Integer> user=new ObjectAndString<>();
        Page<User> page=new Page<>(pageNum,3);
        QueryWrapper qw=new QueryWrapper();
        qw.eq("role",1);
        userMapper.selectPage(page,qw);
        List<User> users=page.getRecords();
        Integer total=(int)page.getTotal();
        System.out.println(total);
        user.setFirst(users);
        user.setSecond(total);
        return user;
    }
    //条件查询用户
    public ObjectAndString<List<User>, Integer> viewLikeUser(String like){
        ObjectAndString<List<User>,Integer> user=new ObjectAndString<>();
        //Page<User> page=new Page<>(pageNum,3);
        QueryWrapper qw=new QueryWrapper();
        qw.eq("role",0);
        qw.like("user_id",like);
        //userMapper.selectPage(page,qw);
        //List<User> users=page.getRecords();
        List<User> users=userMapper.selectList(qw);
        Integer total=users.size();
        System.out.println(total);
        user.setFirst(users);
        user.setSecond(total);
        return user;
    }
    //条件查询管理员
    public ObjectAndString<List<User>, Integer> viewLikeMdUser(String like){
        ObjectAndString<List<User>,Integer> user=new ObjectAndString<>();
        //Page<User> page=new Page<>(pageNum,3);
        QueryWrapper qw=new QueryWrapper();
        qw.eq("role",1);
        qw.like("user_id",like);
        //userMapper.selectPage(page,qw);
        //List<User> users=page.getRecords();
        List<User> users=userMapper.selectList(qw);
        Integer total=users.size();
        System.out.println(total);
        user.setFirst(users);
        user.setSecond(total);
        return user;
    }
    //查看咨询
    public ObjectAndString<List<Guidance>,Integer> viewConsultation(Integer guidanceStatus,Integer pageNum){
        ObjectAndString<List<Guidance>,Integer> guidance=new ObjectAndString<>();
        Page<Guidance> page=new Page<>(pageNum,3);
        QueryWrapper qw=new QueryWrapper();
        qw.eq("guidance_status", guidanceStatus);
        guidanceMapper.selectPage(page,qw);
        List<Guidance> guidances=page.getRecords();
        Integer total=(int)page.getTotal();
        guidance.setFirst(guidances);
        guidance.setSecond(total);
        return guidance;
    }
    //条件查看咨询
    public ObjectAndString<List<Guidance>, Integer> viewLikeConsultation(Integer guidanceStatus, String like){
        ObjectAndString<List<Guidance>,Integer> guidance=new ObjectAndString<>();
        QueryWrapper qw=new QueryWrapper();
        qw.eq("guidance_status", guidanceStatus);
        qw.like("guidance_date",like);
        List<Guidance> guidances=guidanceMapper.selectList(qw);
        Integer total=guidances.size();
        guidance.setFirst(guidances);
        guidance.setSecond(total);
        return guidance;
    }
    //查看单个咨询未回复内容
    public Guidance viewOneConsultation(Integer guidanceId){
        Guidance guidance=guidanceMapper.selectById(guidanceId);
        return guidance;
    }
    //心理咨询回复
    public boolean pcReply(Reply reply){
        Guidance guidance=guidanceMapper.selectById(reply.getGuidanceId());
        guidance.setGuidanceStatus(1);
        int r1=guidanceMapper.updateById(guidance);
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
        if(informations.size()!=0){
            List<Integer> iIds=new ArrayList();//资料的Id
            for (Information information :informations){
                iIds.add(information.getInformationId());
            }
            System.out.println(iIds);

            //根据Id删除上传的资料、和收藏记录
            for (Integer informationId:iIds){
                QueryWrapper qw1=new QueryWrapper();
                qw1.eq("information_id",informationId);
                List<Collect> collects=collectMapper.selectList(qw1);
                System.out.println(collects);
                if(collects!=null){
                    List<Integer> cIds=new ArrayList<>();
                    for(Collect collect :collects ){
                        //cIds.add(collect.getCollectId());
                        collectMapper.deleteById(collect.getCollectId());
                    }
                    //int r11=collectMapper.deleteBatchIds(cIds);
                    int r22=informationMapper.deleteById(informationId);
                }else {
                    int r11=informationMapper.deleteById(informationId);
                }
            }
        }
        List<Collect> c1=collectMapper.selectList(qw);
        List<Mark> m1=markMapper.selectList(qw);
        if(c1!=null){
            int r1=collectMapper.delete(qw);//删除自己收藏的
        }
        if(m1!=null){
            int r3=markMapper.delete(qw);//删除测试成绩
        }
        //查找所有的咨询
        List<Guidance> guidanceList=guidanceMapper.selectList(qw);
        System.out.println(guidanceList);
        //删除咨询的回复
        if(guidanceList!=null){
            for(Guidance guidance : guidanceList){
                QueryWrapper qww=new QueryWrapper();
                Integer guidanceId=guidance.getGuidanceId();
                qww.eq("guidance_id",guidanceId);
                replyMapper.delete(qww);
            }
            int r5=guidanceMapper.delete(qw);//删除咨询
        }
        int r6=userMapper.deleteById(userId);//删除用户
        return true;
    }
    //修改用户状态
    public Boolean editUserStatus(Integer userId, Integer role){
        User user=userMapper.selectById(userId);
        user.setRole(role);
        int r1=userMapper.updateById(user);
        if(r1!=0){
            return true;
        }else {
            return false;
        }
    }
    //给用户发信息
    public Boolean getUserNotice(Guidance guidance){
        guidance.setGuidanceDate(new Date());
        guidance.setGuidanceStatus(2);
        int r1=guidanceMapper.insert(guidance);
        if(r1!=0){
            return true;
        }else {
            return false;
        }
    }
}
