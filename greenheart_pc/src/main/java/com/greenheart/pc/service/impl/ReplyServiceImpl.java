package com.greenheart.pc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.greenheart.pc.dao.ReplyMapper;
import com.greenheart.pc.pojo.Reply;
import com.greenheart.pc.service.ReplyService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Setter(onMethod_={@Resource} )
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements ReplyService {
    private ReplyMapper replyMapper;

    //查看心理咨询回复
    public Reply viewReply(Integer guidanceId){
        Reply reply= query().eq("guidance_id",guidanceId).one();
        return reply;
    }
}
