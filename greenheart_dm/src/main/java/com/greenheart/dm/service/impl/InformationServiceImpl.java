package com.greenheart.dm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.dm.dao.CollectMapper;
import com.greenheart.dm.dao.InformationMapper;
import com.greenheart.dm.pojo.Collect;
import com.greenheart.dm.pojo.Information;
import com.greenheart.dm.service.InformationService;
import com.greenheart.dm.util.ObjectAndString;
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

    //查看所有审核通过的资料
    public ObjectAndString<List<Information>,Integer> allInformation(Integer pageNum){
        Page<Information> page=new Page<>(pageNum,3);
        QueryWrapper qw=new QueryWrapper();
        qw.eq("information_status",1);
        informationMapper.selectPage(page,qw);
        List<Information> informations=page.getRecords();
        Integer total=(int)page.getTotal();
        System.out.println(informations);
        ObjectAndString<List<Information>,Integer> result=new ObjectAndString<>();
        result.setFirst(informations);
        result.setSecond(total);
        System.out.println(result.getFirst());

        return result;
    }
    //搜索资料
    public ObjectAndString<List<Information>, Integer> allLikeInformation(String like){
        QueryWrapper qw=new QueryWrapper();
        qw.like("information_title",like);
        List<Information> informations=informationMapper.selectList(qw);
        Integer total=informations.size();
        ObjectAndString<List<Information>, Integer> information=new ObjectAndString<>();
        information.setFirst(informations);
        information.setSecond(total);
        return information;
    }
    //查看资料
    public Information informationId(Integer informationId){
        Information information=informationMapper.selectById(informationId);
        return information;
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
            if(r1!=0&&r2!=0){
                return true;
            }else{
                return false;
            }
        }else {
            int r1=informationMapper.deleteById(informationId);
            QueryWrapper qw1=new QueryWrapper();
            qw1.eq("information_id",informationId);
            if(r1!=0){
                return true;
            }else{
                return false;
            }
        }
    }
}
