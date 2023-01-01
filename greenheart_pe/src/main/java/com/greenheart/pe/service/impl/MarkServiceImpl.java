package com.greenheart.pe.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.pe.dao.MarkMapper;
import com.greenheart.pe.pojo.Mark;
import com.greenheart.pe.service.MarkService;
import com.greenheart.pe.util.ObjectAndString;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Setter(onMethod_ ={@Resource})
public class MarkServiceImpl extends ServiceImpl<MarkMapper, Mark> implements MarkService {
    private MarkMapper markMapper;

    //查看成绩
    public ObjectAndString<List<Mark>,Integer> viewScore(Integer userId,Integer pageNum){
        ObjectAndString<List<Mark>,Integer> mark=new ObjectAndString<>();
        Page<Mark> page=new Page<>(pageNum,3);
        QueryWrapper qw=new QueryWrapper();
        qw.eq("user_id",userId);
        markMapper.selectPage(page,qw);
        List<Mark> marks=page.getRecords();
        Integer total=(int)page.getTotal();
        mark.setFirst(marks);
        mark.setSecond(total);
        return mark;
    }
    //搜索查看成绩
    public ObjectAndString<List<Mark>,Integer> viewLikeScore(Integer userId,String like){
        ObjectAndString<List<Mark>,Integer> mark=new ObjectAndString<>();
        List<Mark> markList=new ArrayList<>();
        QueryWrapper qw=new QueryWrapper();
        qw.eq("user_id",userId);
        List<Mark> marks=markMapper.selectList(qw);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(String.valueOf(like));
        for(Mark mark1:marks){
            if(dateFormat.format(mark1.getMarkDate()).contains(String.valueOf(like))||dateFormat.format(mark1.getMarkDate()).equals(String.valueOf(like))){
                markList.add(mark1);
            }
        }
        Integer total=markList.size();
        mark.setFirst(markList);
        mark.setSecond(total);
        return mark;
    }

}
