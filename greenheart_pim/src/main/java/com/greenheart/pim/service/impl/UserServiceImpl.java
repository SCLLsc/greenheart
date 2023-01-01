package com.greenheart.pim.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.pim.dao.UserMapper;
import com.greenheart.pim.pojo.User;
import com.greenheart.pim.service.UserService;
import com.greenheart.pim.util.MD5Util;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;



@Service
@Slf4j
@Setter(onMethod_ ={@Resource} )
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
   private UserMapper userMapper;
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
   public boolean updateMyself(Integer userId,String userName,String email){
      User user=userMapper.selectById(userId);
      if(userName!=null){
         user.setUserName(userName);
      }
      if(email!=null){
         user.setEmail(email);
      }

      if(userMapper.updateById(user)==1){
         return true;
      }else {
         return false;
      }
   }

   // 修改密码
   public boolean updateMyPwd(Integer userId,String userPwd){
      User user=userMapper.selectById(userId);
      if(userPwd!=null){
         MD5Util md5=new MD5Util();
         String encryptPwd=md5.convertMD5(userPwd);//MD5加密
         user.setUserPwd(encryptPwd);
      }
      if(userMapper.updateById(user)==1){
         return true;
      }else {
         return false;
      }
   }

}
