package com.greenheart.pc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.pc.dao.GuidanceMapper;
import com.greenheart.pc.pojo.Guidance;
import com.greenheart.pc.service.GuidanceService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
@Setter(onMethod_={@Resource} )
public class GuidanceServiceImpl extends ServiceImpl<GuidanceMapper, Guidance> implements GuidanceService {
    private GuidanceMapper guidanceMapper;

    //心理咨询
    public boolean consult(Guidance guidance){
        guidance.setGuidanceDate(new Date());
        guidance.setGuidanceStatus(0);
        int result=guidanceMapper.insert(guidance);
        if(result!=0){
            return true;
        }else{
            return false;
        }
    }

    //查看咨询
    public List<Guidance> viewConsultation(Integer userId,Integer guidanceStatus,Integer pageNum){
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_id",userId);
        map.put("guidance_status", guidanceStatus);
        QueryWrapper qw=new QueryWrapper();
        qw.allEq(map,false);
        Page<Guidance> page=new Page<>(pageNum,3);
        List<Guidance> guidances=guidanceMapper.selectPage(page,qw).getRecords();
        long total=page.getTotal();
        long current=page.getCurrent();
        long pages=page.getPages();
       return guidances;
    }

}
