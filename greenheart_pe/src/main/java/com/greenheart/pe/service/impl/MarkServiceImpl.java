package com.greenheart.pe.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.pe.dao.MarkMapper;
import com.greenheart.pe.pojo.Mark;
import com.greenheart.pe.service.MarkService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
@Setter(onMethod_ ={@Resource})
public class MarkServiceImpl extends ServiceImpl<MarkMapper, Mark> implements MarkService {
    private MarkMapper markMapper;

    //查看成绩
    public List<Mark> viewScore(Integer userId,Integer pageNum){
        QueryWrapper qw=new QueryWrapper();
        qw.eq("user_id",userId);
        Page<Mark> page=new Page<>(pageNum,3);
        markMapper.selectPage(page,qw);
        List<Mark> marks=page.getRecords();
        long total=page.getTotal();
        long current=page.getCurrent();
        long pages=page.getPages();
        return marks;
    }

}
