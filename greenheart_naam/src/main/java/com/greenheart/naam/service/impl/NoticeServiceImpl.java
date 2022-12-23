package com.greenheart.naam.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.naam.dao.NoticeMapper;
import com.greenheart.naam.pojo.Notice;
import com.greenheart.naam.service.NoticeService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Setter(onMethod_={@Resource} )
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    private NoticeMapper noticeMapper;

    //查看通知公告
    public List<Notice> allNotice(){
        List<Notice> notices=noticeMapper.selectList(null);
        return notices;
    }
    //新增公告
    public boolean addNotice(Notice notice){
        notice.setCreationTime(new Date());
        int result=noticeMapper.insert(notice);
        if(result!=0){
            return true;
        }else{
            return false;
        }
    }

    //修改公告
    public boolean updateNotice(Notice notice){
        notice.setUpdateTime(new Date());
        int result=noticeMapper.updateById(notice);
        if(result!=0){
            return true;
        }else{
            return false;
        }
    }

}
