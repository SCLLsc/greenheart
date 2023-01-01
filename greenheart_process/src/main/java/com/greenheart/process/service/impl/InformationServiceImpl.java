package com.greenheart.process.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.process.dao.InformationMapper;
import com.greenheart.process.pojo.Information;
import com.greenheart.process.service.InformationService;

import com.greenheart.process.util.ObjectAndString;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@Setter(onMethod_= {@Resource})
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information> implements InformationService{
    private InformationMapper informationMapper;

    //查看所有待审核资料
    public ObjectAndString<List<Information>,Integer> allNoInformation(Integer pageNum){
        Page<Information> page=new Page<>(pageNum,3);
        QueryWrapper qw=new QueryWrapper();
        qw.eq("information_status",0);
        informationMapper.selectPage(page,qw);
        List<Information> informations=page.getRecords();
        System.out.println(informations);
        Integer total=(int)page.getTotal();
        ObjectAndString<List<Information>,Integer> result=new ObjectAndString<>();
        result.setFirst(informations);
        result.setSecond(total);
        System.out.println(result.getFirst());


        return result;
    }

    //搜索待审核资料
    public ObjectAndString<List<Information>, Integer> allLikeNoInformation(String like){
        QueryWrapper qw=new QueryWrapper();
        qw.eq("information_status",0);
        List<Information> informations=informationMapper.selectList(qw);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Information> informationList=new ArrayList<>();
        for(Information information:informations){
            if(dateFormat.format(information.getUploadTime()).contains(like)|dateFormat.format(information.getUploadTime()).equals(like)){
                informationList.add(information);
            }
        }
        Integer total=informationList.size();
        ObjectAndString<List<Information>, Integer> information1=new ObjectAndString<>();
        information1.setFirst(informationList);
        information1.setSecond(total);
        return information1;
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
