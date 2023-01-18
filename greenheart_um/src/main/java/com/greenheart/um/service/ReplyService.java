package com.greenheart.um.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.greenheart.um.pojo.Guidance;
import com.greenheart.um.pojo.Reply;
import com.greenheart.um.pojo.User;
import com.greenheart.um.util.ObjectAndString;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ReplyService extends IService<Reply> {
    default ObjectAndString<List<Guidance>,Integer> viewConsultation(Integer guidanceStatus,Integer pageNum){return null;}

    default boolean pcReply(Reply reply){return false;}

    default boolean cancellation(Integer userId){return false;}

    default Guidance viewOneConsultation(Integer guidanceId){return null;}

    default ObjectAndString<List<User>,Integer> viewAllUser(Integer pageNum){return null;}

    default ObjectAndString<List<User>, Integer> viewLikeUser(String like){return null;}

    default ObjectAndString<List<Guidance>, Integer> viewLikeConsultation(Integer guidanceStatus, String like){return null;}

    default Boolean editUserStatus(Integer userId, Integer role){return false;}

    default Boolean getUserNotice(Guidance guidance){return false;}

    default ObjectAndString<List<User>, Integer> viewMdAllUser(Integer pageNum){return null;}

    default ObjectAndString<List<User>, Integer> viewLikeMdUser(String like){return null;}
}
