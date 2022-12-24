package com.greenheart.collect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.collect.dao.CollectMapper;
import com.greenheart.collect.dao.InformationMapper;
import com.greenheart.collect.dao.PictureMapper;
import com.greenheart.collect.pojo.Collect;
import com.greenheart.collect.pojo.Information;
import com.greenheart.collect.pojo.Picture;
import com.greenheart.collect.service.CollectService;
import com.greenheart.collect.util.ObjectAndString;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Setter(onMethod_={@Resource} )
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {
    private CollectMapper collectMapper;
    private InformationMapper informationMapper;
    private PictureMapper pictureMapper;

    //查看收藏的资料
    public ObjectAndString<List<Information>,List<Picture>> selectAllCollect(Integer userId){
        QueryWrapper<Collect> qw=new QueryWrapper();
        qw.eq("user_id",userId);
        List<Collect> collects=collectMapper.selectList(qw);
        List<Information> informations=new ArrayList<Information>();
        for(Collect collect:collects){
            informations.add(informationMapper.selectById(collect.getInformationId()));
        }
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
        return result;
    }

    //收藏资料
    public boolean addCollect(Integer userId,Integer informationId){
        Collect collect=new Collect();
        collect.setUserId(userId);
        collect.setInformationId(informationId);
        int result=collectMapper.insert(collect);
        if(result!=0){
            return true;
        }else{
            return false;
        }
    }
    //取消收藏
    public boolean removeCollect(Integer userId,Integer informationId){
        QueryWrapper qw=new QueryWrapper();
        qw.eq("user_id",userId);
        qw.eq("information_id",informationId);
        int result=collectMapper.delete(qw);
        if(result!=0){
            return true;
        }else{
            return false;
        }
    }
}
