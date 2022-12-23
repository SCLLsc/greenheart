package com.greenheart.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.user.dao.*;
import com.greenheart.user.pojo.Collect;
import com.greenheart.user.pojo.Guidance;
import com.greenheart.user.pojo.Information;
import com.greenheart.user.pojo.User;
import com.greenheart.user.service.UserService;
import com.greenheart.user.util.MD5Util;
import com.greenheart.user.util.ObjectAndString;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Setter(onMethod_ ={@Resource})
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
   private UserMapper userMapper;
   private CollectMapper collectMapper;
   private GuidanceMapper guidanceMapper;
   private InformationMapper informationMapper;
   private MarkMapper markMapper;
   private PictureMapper pictureMapper;
   private ReplyMapper replyMapper;
   private TrialMapper trialMapper;
   //private StringRedisTemplate redisTemplate;

   //注册
   public boolean register(User user){
       MD5Util md5=new MD5Util();
       String encryptPwd=md5.convertMD5(user.getUserPwd());//MD5加密
       user.setUserPwd(encryptPwd);
       int result=userMapper.insert(user);
       if(result==1){
           return true;
       }else {
           return false;
       }
   }
   //登录
   public ObjectAndString<User,String> login(String email, String userPwd){
       ObjectAndString<User,String> result=new ObjectAndString<>();
       MD5Util md5=new MD5Util();
       User user = query().eq("email", email).one();
       if(user!=null){
           String decryptPwd =md5.convertMD5(md5.convertMD5(user.getUserPwd()));//MD5解密
           if(decryptPwd.equals(userPwd)){
               result.setFirst(user);
               result.setSecond("登陆成功");
           }else {
               result.setSecond("密码错误");
           }
       }else {
           result.setSecond("邮箱错误或尚未注册");
       }
       return result;

   }

   //找回密码
    public String retrievePwd(String email){
       MD5Util md5=new MD5Util();
       User user=query().eq("email",email).one();
        if(user!=null){
            String decryptPwd =md5.convertMD5(md5.convertMD5(user.getUserPwd()));//MD5解密
            return decryptPwd;
        }else {
            return "该邮箱尚未注册";
        }
    }

    //根据邮箱找用户
    public User findByEmail(String email) {
        User user=query().eq("email",email).one();
        return user;
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
