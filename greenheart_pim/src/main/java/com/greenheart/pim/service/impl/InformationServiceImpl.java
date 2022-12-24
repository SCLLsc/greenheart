package com.greenheart.pim.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.pim.dao.CollectMapper;
import com.greenheart.pim.dao.InformationMapper;
import com.greenheart.pim.dao.PictureMapper;
import com.greenheart.pim.pojo.Collect;
import com.greenheart.pim.pojo.Information;
import com.greenheart.pim.pojo.Picture;
import com.greenheart.pim.service.InformationService;
import com.greenheart.pim.util.ObjectAndString;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
@Setter(onMethod_= {@Resource})
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information> implements InformationService{
    private InformationMapper informationMapper;
    private CollectMapper collectMapper;
    private PictureMapper pictureMapper;

    // 查看上传的资料
    public ObjectAndString<List<Information>,List<Picture>> myInformation(String userId,Integer informationStatus){
        QueryWrapper qw=new QueryWrapper();
        qw.eq("user_id",userId);
        qw.eq("information_Status",informationStatus);
        List<Information> informations=informationMapper.selectList(qw);
        List<Picture> pPaths=new ArrayList();
        ObjectAndString<List<Information>,List<Picture>> result=new ObjectAndString<>();
        for(Information information:informations){
            QueryWrapper qw1=new QueryWrapper();
            qw1.eq("information_id",information.getInformationId());
            pPaths.add(pictureMapper.selectOne(qw1));
        }
        result.setFirst(informations);
        result.setSecond(pPaths);
        return result;
    }

    // 删除上传的资料
    public boolean removeInformation(String informationId){
        QueryWrapper qw=new QueryWrapper();
        qw.eq("information_id",informationId);
        List<Collect> collects=collectMapper.selectList(qw);
        if(collects!=null){
            List cIds=new ArrayList();
            for(Collect collect :collects ){
                cIds.add(collect.getCollectId());
            }
            QueryWrapper qw1=new QueryWrapper();
            qw1.eq("information_id",informationId);
            int r1=collectMapper.deleteBatchIds(cIds);
            int r2=informationMapper.deleteById(informationId);
            int r3=pictureMapper.delete(qw1);
            if(r1!=0&&r2!=0&&r3!=0){
                return true;
            }else{
                return false;
            }
        }else {
            int r1=informationMapper.deleteById(informationId);
            QueryWrapper qw1=new QueryWrapper();
            qw1.eq("information_id",informationId);
            int r3=pictureMapper.delete(qw1);
            if(r1!=0&&r3!=0){
                return true;
            }else{
                return false;
            }
        }
    }
}
