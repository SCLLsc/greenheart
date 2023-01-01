package com.greenheart.ud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.ud.dao.InformationMapper;
import com.greenheart.ud.dao.UserMapper;
import com.greenheart.ud.pojo.Information;
import com.greenheart.ud.pojo.User;
import com.greenheart.ud.service.InformationService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Slf4j
@Setter(onMethod_= {@Resource})
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information> implements InformationService{
    private InformationMapper informationMapper;
    private UserMapper userMapper;


    //上传资料
    public String uploadInformation(Integer userId, String informationType, String informationTitle, String informationContent){
      QueryWrapper qw=new QueryWrapper();
      qw.eq("user_id",userId);
      User user=userMapper.selectOne(qw);
      Information information=new Information();
      if(user.getRole()==0){
          information.setInformationStatus(0);
      }else {
          information.setInformationStatus(1);
      }
      information.setUserId(userId);
      information.setInformationType(informationType);
      information.setInformationTitle(informationTitle);
      information.setInformationContent(informationContent);
      information.setUploadTime(new Date());
      int result=informationMapper.insert(information);
      if(result!=0){
          return "资料上传成功";
      }else {
          return "资料上传失败";
      }
    }
}
