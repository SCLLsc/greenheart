package com.greenheart.pim.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.pim.dao.CollectMapper;
import com.greenheart.pim.dao.InformationMapper;
import com.greenheart.pim.pojo.Collect;
import com.greenheart.pim.pojo.Information;
import com.greenheart.pim.service.InformationService;
import com.greenheart.pim.util.ObjectAndString;
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
    private CollectMapper collectMapper;


    // 查看上传的资料
    public ObjectAndString<List<Information>,Integer> myInformation(String userId,Integer informationStatus,Integer pageNum){
        Page<Information> page=new Page<>(pageNum,3);
        QueryWrapper qw=new QueryWrapper();
        qw.eq("user_id",userId);
        qw.eq("information_Status",informationStatus);
        informationMapper.selectPage(page,qw);
        List<Information> informations=page.getRecords();
        Integer total=(int)page.getTotal();
        ObjectAndString<List<Information>,Integer> result=new ObjectAndString<>();
        result.setFirst(informations);
        result.setSecond(total);
        return result;
    }
    //搜索上传的资料
    public ObjectAndString<List<Information>, Integer> myLikeInformation(String userId, Integer informationStatus, String like){
       QueryWrapper qw=new QueryWrapper();
       qw.eq("information_status",informationStatus);
       qw.like("information_title",like);
        List<Information> informations=informationMapper.selectList(qw);
        Integer total=informations.size();
        ObjectAndString<List<Information>, Integer> information=new ObjectAndString<>();
        information.setFirst(informations);
        information.setSecond(total);
        return information;
    }
    // 删除上传的资料
    public boolean removeInformation(Integer informationId){
        Information information=informationMapper.selectById(informationId);
        if(information.getInformationStatus()==0){
            informationMapper.deleteById(informationId);
            return true;
        }else if(information.getInformationStatus()==1){
            QueryWrapper qw=new QueryWrapper();
            qw.eq("information_id",informationId);
            List<Collect> collects=collectMapper.selectList(qw);
            if(collects!=null){
                for(Collect collect :collects ){
                    collectMapper.deleteById(collect.getCollectId());
                }
                int r2=informationMapper.deleteById(informationId);
                if(r2!=0){
                    return true;
                }else{
                    return false;
                }
            }else {
                int r1=informationMapper.deleteById(informationId);
                if(r1!=0){
                    return true;
                }else{
                    return false;
                }
            }
        }else{
            informationMapper.deleteById(informationId);
            return true;
        }
    }
}
