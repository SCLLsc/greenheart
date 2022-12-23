package com.greenheart.um.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.greenheart.um.pojo.Guidance;
import com.greenheart.um.pojo.Reply;
import com.greenheart.um.util.ObjectAndString;

import java.util.List;

public interface ReplyService extends IService<Reply> {
    default List<Guidance> viewConsultation(Integer guidanceStatus, Integer pageNum){return null;}

    default boolean pcReply(Reply pcReply){return false;}

    default boolean cancellation(Integer userId){return false;}

    default Guidance viewOneConsultation(Integer guidanceId){return null;}
}
