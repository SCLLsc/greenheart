package com.greenheart.pim.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.pim.dao.PictureMapper;
import com.greenheart.pim.dao.UserMapper;

import com.greenheart.pim.pojo.Picture;
import com.greenheart.pim.pojo.User;
import com.greenheart.pim.service.UserService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
//import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Random;


@Service
@Slf4j
@Setter(onMethod_ ={@Resource} )
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
   private UserMapper userMapper;
   private PictureMapper pictureMapper;
   //private StringRedisTemplate redisTemplate;


   //个人信息查看
   public User myself(String userId){
      User user=query().eq("user_id",userId).one();
      return user;
   }

   // 修改个人信息
   public boolean updateMyself(User user){
      if(userMapper.updateById(user)==1){
         return true;
      }else {
         return false;
      }
   }

   //头像上传
   public String upload(Integer userId, MultipartFile fileImage){
      log.info("文件开始上传");
      String workUrl=null;
      if(!fileImage.isEmpty()) {
         String path="E:\\IDEA部分项目\\greenheart\\greenheart_pim\\src\\main\\resources\\images";
         log.info("上传文件存储的目录："+path);

         String oldFileName=fileImage.getOriginalFilename();
         log.info("上传文件的原文件名："+oldFileName);

         String subflix= FilenameUtils.getExtension(oldFileName);
         log.info("上传文件的扩展名："+subflix);

         int fileSize=400000;

         if(fileSize<fileImage.getSize()) {
            return "图片过大！";
         }else if(subflix.equalsIgnoreCase("jpg")
                 ||subflix.equalsIgnoreCase("png")
                 ||subflix.equalsIgnoreCase("gif")
                 ||subflix.equalsIgnoreCase("jpeg")){
            Random random=new Random();
            String fileName=System.currentTimeMillis()+random.nextInt(10)+"."+subflix;
            File targetFile=new File(path,fileName);
            if(targetFile.exists()) {
               targetFile.mkdir();
            }
            try {
               fileImage.transferTo(targetFile);
               workUrl="upload/"+fileName;
            } catch (Exception e) {
               // TODO 自动生成的 catch 块
               e.printStackTrace();
               return "上传失败";
            }
         }else {
            return "上传失败,图片格式不对";
         }
         Picture picture=new Picture();
         picture.setUserId(userId);
         picture.setPictureType(2);
         picture.setPath(workUrl);
         int r=pictureMapper.insert(picture);
         return "头像上传成功";
      }else{
         return "图片为空,头像上传失败";
      }
   }
   //修改头像
   public String updatePhoto(Integer userId, MultipartFile fileImage){
      log.info("文件开始上传");
      String workUrl=null;
      if(!fileImage.isEmpty()) {
         String path="E:\\IDEA部分项目\\greenheart\\greenheart_pim\\src\\main\\resources\\images";
         log.info("上传文件存储的目录："+path);

         String oldFileName=fileImage.getOriginalFilename();
         log.info("上传文件的原文件名："+oldFileName);

         String subflix= FilenameUtils.getExtension(oldFileName);
         log.info("上传文件的扩展名："+subflix);

         int fileSize=400000;

         if(fileSize<fileImage.getSize()) {
            return "图片过大！";
         }else if(subflix.equalsIgnoreCase("jpg")
                 ||subflix.equalsIgnoreCase("png")
                 ||subflix.equalsIgnoreCase("gif")
                 ||subflix.equalsIgnoreCase("jpeg")){
            Random random=new Random();
            String fileName=System.currentTimeMillis()+random.nextInt(10)+"."+subflix;
            File targetFile=new File(path,fileName);
            if(targetFile.exists()) {
               targetFile.mkdir();
            }
            try {
               fileImage.transferTo(targetFile);
               workUrl="upload/"+fileName;
            } catch (Exception e) {
               // TODO 自动生成的 catch 块
               e.printStackTrace();
               return "上传失败";
            }
         }else {
            return "上传失败,图片格式不对";
         }
         QueryWrapper qw=new QueryWrapper();
         qw.eq("user_id",userId);
         Picture picture=pictureMapper.selectOne(qw);
         picture.setPath(workUrl);
         int r=pictureMapper.insert(picture);
         return "头像上传成功";
      }else{
         return "图片为空,头像上传失败";
      }
   }

}
