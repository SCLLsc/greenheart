package com.greenheart.process.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.process.dao.InformationMapper;
import com.greenheart.process.dao.PictureMapper;
import com.greenheart.process.pojo.Information;
import com.greenheart.process.pojo.Picture;
import com.greenheart.process.service.InformationService;

import com.greenheart.process.util.ObjectAndString;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@Setter(onMethod_= {@Resource})
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information> implements InformationService{
    private InformationMapper informationMapper;
    private PictureMapper pictureMapper;

    //查看所有待审核资料
    public ObjectAndString<List<Information>,List<Picture>> allNoInformation(Integer pageNum){
        Page<Information> page=new Page<>(pageNum,10);
        QueryWrapper qw=new QueryWrapper();
        qw.eq("information_status",0);
        informationMapper.selectPage(page,qw);
        List<Information> informations=page.getRecords();
        System.out.println(informations);
        List<Picture> pPaths=new ArrayList();
        ObjectAndString<List<Information>,List<Picture>> result=new ObjectAndString<>();
        for(Information information:informations){
            QueryWrapper qw1=new QueryWrapper();
            qw1.eq("information_id",information.getInformationId());
            pPaths.add(pictureMapper.selectOne(qw1));
        }
        result.setFirst(informations);
        result.setSecond(pPaths);
        System.out.println(result.getFirst());
        System.out.println(pPaths);

        return result;
    }

    //审核资料
    public boolean process(Integer informationId, Integer process) {
        if(process==1){
            Information information=informationMapper.selectById(informationId);
            information.setInformationStatus(1);
            informationMapper.updateById(information);
            return true;
        }else{
            Information information=informationMapper.selectById(informationId);
            information.setInformationStatus(2);
            informationMapper.updateById(information);
            return false;
        }
    }
}
