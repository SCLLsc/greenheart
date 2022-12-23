package com.greenheart.ud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.ud.dao.InformationMapper;
import com.greenheart.ud.dao.PictureMapper;
import com.greenheart.ud.dao.UserMapper;
import com.greenheart.ud.pojo.Information;
import com.greenheart.ud.pojo.Picture;
import com.greenheart.ud.service.InformationService;
import com.greenheart.ud.util.ObjectAndString;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.Random;

@Service
@Slf4j
@Setter(onMethod_= {@Resource})
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information> implements InformationService{
    private InformationMapper informationMapper;
    private PictureMapper pictureMapper;
    private UserMapper userMapper;

    //上传资料
    public String upload(ObjectAndString<Information,String> information, MultipartFile fileImage){
        log.info("文件开始上传");
        String workUrl=null;
        if(!fileImage.isEmpty()) {
            String path="E:\\IDEA部分项目\\greenheart\\greenheart_ud\\src\\main\\resources\\images";
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
            Information information1=information.getFirst();
            QueryWrapper qw=new QueryWrapper();
            qw.eq("information_id",information.getFirst().getInformationId());
            int role=userMapper.selectOne(qw).getRole();
            if(role==0){
                information1.setInformationStatus(0);
            }else {
                information1.setInformationStatus(1);
            }
            Picture picture=new Picture();
            picture.setInformationId(information.getFirst().getInformationId());
            picture.setPictureType(1);
            picture.setPath(workUrl);
            information1.setUploadTime(new Date());
            information1.setUserId(Integer.valueOf(information.getSecond()));
            int r1=informationMapper.insert(information.getFirst());
            int r2=pictureMapper.insert(picture);
            return "资料上传成功";

        }else{
            return "图片为空,资料添加失败";
        }
    }
}
