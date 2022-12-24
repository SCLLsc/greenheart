package com.greenheart.um.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.greenheart.um.pojo.Guidance;
import com.greenheart.um.pojo.Reply;
import com.greenheart.um.pojo.User;
import com.greenheart.um.util.ObjectAndString;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ReplyService extends IService<Reply> {
    default List<Guidance> viewConsultation(Integer guidanceStatus){return null;}

    default boolean pcReply(Integer guidanceId,String replyContent){return false;}

    default boolean cancellation(Integer userId){return false;}

    default Guidance viewOneConsultation(Integer guidanceId){return null;}

    default List<User> viewAllUser(){return null;}
}
