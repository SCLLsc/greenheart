package com.greenheart.collect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.collect.dao.CollectMapper;
import com.greenheart.collect.dao.InformationMapper;
import com.greenheart.collect.pojo.Collect;
import com.greenheart.collect.pojo.Information;
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

    //查看收藏的资料
    public ObjectAndString<List<Information>,Integer> selectAllCollect(Integer userId,Integer pageNum){
        Page<Collect> page=new Page<>(pageNum,3);
        QueryWrapper<Collect> qw=new QueryWrapper();
        qw.eq("user_id",userId);
        collectMapper.selectPage(page,qw);
        List<Collect> collects=page.getRecords();
        Integer total=(int)page.getTotal();
        List<Information> informations=new ArrayList<>();
        for(Collect collect:collects){
            informations.add(informationMapper.selectById(collect.getInformationId()));
        }
        System.out.println(informations);
        ObjectAndString<List<Information>,Integer> result=new ObjectAndString<>();
        result.setFirst(informations);
        result.setSecond(total);
        return result;
    }

    //搜索收藏的资料
    public ObjectAndString<List<Information>, Integer> selectLikeAllCollect(Integer userId, String like){
        QueryWrapper qw=new QueryWrapper();
        qw.eq("user_id",userId);
        qw.like("information_title",like);
        List<Information> informations=informationMapper.selectList(qw);
        Integer total=informations.size();
        ObjectAndString<List<Information>, Integer> information=new ObjectAndString<>();
        information.setFirst(informations);
        information.setSecond(total);
        return information;
    }
    //收藏资料
    public boolean addCollect(Integer userId,Integer informationId){
        QueryWrapper qw=new QueryWrapper();
        qw.eq("user_id",userId);
        qw.eq("information_id",informationId);
        Collect oldCollect=collectMapper.selectOne(qw);
        if(oldCollect!=null){
            return false;
        }else {
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
