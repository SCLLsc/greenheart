package com.greenheart.pim.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.pim.dao.GuidanceMapper;
import com.greenheart.pim.dao.UserMapper;
import com.greenheart.pim.pojo.Guidance;
import com.greenheart.pim.pojo.User;
import com.greenheart.pim.service.UserService;
import com.greenheart.pim.util.MD5Util;
import com.greenheart.pim.util.ObjectAndString;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import javax.annotation.Resource;
import java.util.List;


@Service
@Slf4j
@Setter(onMethod_ ={@Resource} )
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
   private UserMapper userMapper;
   private GuidanceMapper guidanceMapper;
   //private StringRedisTemplate redisTemplate;


   //个人信息查看
   public User myself(String userId){
      MD5Util md5=new MD5Util();
      User user=query().eq("user_id",userId).one();
      String decryptPwd =md5.convertMD5(user.getUserPwd());//MD5解密
      user.setUserPwd(decryptPwd);
      return user;
   }

   // 修改个人信息
   public boolean updateMyself(User user){
      User user1=userMapper.selectById(user.getUserId());
      if(user.getUserName()!=null){
         user1.setUserName(user.getUserName());
      }
      if(user.getEmail()!=null){
         QueryWrapper qw=new QueryWrapper();
         qw.eq("email",user.getEmail());
         User oldUser=userMapper.selectOne(qw);
         if(oldUser.getUserId()!=user.getUserId()){
            return false;
         }else {
            user1.setEmail(user.getEmail());
         }
      }

      if(userMapper.updateById(user1)==1){
         return true;
      }else {
         return false;
      }
   }

   // 修改密码
   public boolean updateMyPwd(User user){
      User user1=userMapper.selectById(user.getUserId());
      if(user.getUserPwd()!=null){
         MD5Util md5=new MD5Util();
         String encryptPwd=md5.convertMD5(user.getUserPwd());//MD5加密
         user1.setUserPwd(encryptPwd);
      }
      if(userMapper.updateById(user1)==1){
         return true;
      }else {
         return false;
      }
   }

   //查看咨询
   public ObjectAndString<List<Guidance>,Integer> viewMyConsultation(Integer userId,Integer guidanceStatus, Integer pageNum){
      ObjectAndString<List<Guidance>,Integer> guidance=new ObjectAndString<>();
      Page<Guidance> page=new Page<>(pageNum,3);
      QueryWrapper qw=new QueryWrapper();
      qw.eq("user_id",userId);
      qw.eq("guidance_status", guidanceStatus);
      guidanceMapper.selectPage(page,qw);
      List<Guidance> guidances=page.getRecords();
      Integer total=(int)page.getTotal();
      guidance.setFirst(guidances);
      guidance.setSecond(total);
      return guidance;
   }
   //条件查看咨询
   public ObjectAndString<List<Guidance>, Integer> viewLikeMyConsultation(Integer userId,Integer guidanceStatus, String like){
      ObjectAndString<List<Guidance>,Integer> guidance=new ObjectAndString<>();
      QueryWrapper qw=new QueryWrapper();
      qw.eq("user_id",userId);
      qw.eq("guidance_status", guidanceStatus);
      qw.like("guidance_date",like);
      List<Guidance> guidances=guidanceMapper.selectList(qw);
      Integer total=guidances.size();
      guidance.setFirst(guidances);
      guidance.setSecond(total);
      return guidance;
   }

}
