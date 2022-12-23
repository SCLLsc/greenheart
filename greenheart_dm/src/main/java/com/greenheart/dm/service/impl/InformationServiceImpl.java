package com.greenheart.dm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.dm.dao.CollectMapper;
import com.greenheart.dm.dao.InformationMapper;
import com.greenheart.dm.dao.PictureMapper;
import com.greenheart.dm.pojo.Collect;
import com.greenheart.dm.pojo.Information;
import com.greenheart.dm.pojo.Picture;
import com.greenheart.dm.service.InformationService;
import com.greenheart.dm.util.ObjectAndString;
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

    //查看所有审核通过的资料
    public ObjectAndString<List<Information>,List<Picture>> allInformation(Integer pageNum){
        Page<Information> page=new Page<>(pageNum,3);
        QueryWrapper qw=new QueryWrapper();
        qw.eq("information_status",1);
        informationMapper.selectPage(page,qw);
        List<Information> informations=page.getRecords();
        System.out.println(informations);
        List<Picture> pPaths=new ArrayList();
        ObjectAndString<List<Information>,List<Picture>> result=new ObjectAndString<>();
        Map map=new HashMap();
        int key=0;
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
    //查看资料
    public ObjectAndString<Information, Picture> informationId(Integer informationId){
        Information information=informationMapper.selectById(informationId);
        QueryWrapper qw=new QueryWrapper();
        qw.eq("information_id",informationId);
        Picture picture=pictureMapper.selectOne(qw);
        ObjectAndString<Information,Picture> result=new ObjectAndString<>();
        result.setFirst(information);
        result.setSecond(picture);
        return result;
    }
    //删除资料
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
