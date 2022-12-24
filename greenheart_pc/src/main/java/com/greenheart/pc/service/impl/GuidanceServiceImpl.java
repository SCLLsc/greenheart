package com.greenheart.pc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.pc.dao.GuidanceMapper;
import com.greenheart.pc.dao.ReplyMapper;
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
    private ReplyMapper replyMapper;

    //心理咨询
    public boolean consult(Integer userId,String guidanceContent){
        Guidance guidance=new Guidance();
        guidance.setUserId(userId);
        guidance.setGuidanceContent(guidanceContent);
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
    public List<Guidance> viewConsultation(Integer userId,Integer guidanceStatus){
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_id",userId);
        map.put("guidance_status", guidanceStatus);
        QueryWrapper qw=new QueryWrapper();
        qw.allEq(map,false);
        List<Guidance> guidances=guidanceMapper.selectList(qw);
       return guidances;
    }
    //删除已回复的咨询
    public boolean delGuidance(Integer guidanceId){
        QueryWrapper qw=new QueryWrapper();
        qw.eq("guidance_id",guidanceId);
        int r1=guidanceMapper.deleteById(guidanceId);
        int r2=replyMapper.delete(qw);
        if(r1!=0&&r2!=0){
            return true;
        }else {
            return false;
        }
    }

}
