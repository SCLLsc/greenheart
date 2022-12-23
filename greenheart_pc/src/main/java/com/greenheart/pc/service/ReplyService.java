package com.greenheart.pc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.greenheart.pc.pojo.Reply;

import java.util.List;


public interface ReplyService extends IService<Reply> {
    default Reply viewReply(Integer guidanceId){return null;}
}
