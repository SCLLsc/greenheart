package com.greenheart.pim.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.greenheart.pim.pojo.Guidance;
import com.greenheart.pim.pojo.User;
import com.greenheart.pim.util.ObjectAndString;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService extends IService<User>{
    default User myself(String userId){return null;}

    default boolean updateMyself(User user){return true;}

    default boolean updateMyPwd(User user){return true;}

    default ObjectAndString<List<Guidance>, Integer> viewMyConsultation(Integer userId,Integer guidanceStatus, Integer pageNum){return  null;}

    default ObjectAndString<List<Guidance>, Integer> viewLikeMyConsultation(Integer userId, Integer guidanceStatus, String like){return  null;}
}
