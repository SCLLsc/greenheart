package com.greenheart.collect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.collect.dao.CollectMapper;
import com.greenheart.collect.pojo.Collect;
import com.greenheart.collect.service.CollectService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
@Setter(onMethod_={@Resource} )
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {
    private CollectMapper collectMapper;

    //查看所有资料
    public List<Collect> selectAllCollect(Integer userId, Integer pageNum){
        QueryWrapper<Collect> qw=new QueryWrapper();
        qw.eq("user_id",userId);
        Page<Collect> page=new Page<>(pageNum,3);
        collectMapper.selectPage(page,qw);
        List<Collect> collects=page.getRecords();
        return collects;
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
}
