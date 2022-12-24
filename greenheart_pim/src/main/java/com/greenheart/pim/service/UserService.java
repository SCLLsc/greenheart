package com.greenheart.pim.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.greenheart.pim.pojo.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends IService<User>{
    default User myself(String userId){return null;}

    default boolean updateMyself(Integer userId,String userName,String email){return true;}

    default String upload(Integer userId,MultipartFile fileImage){return  null;}

    default String updatePhoto(Integer userId,MultipartFile fileImage){return  null;}

    default boolean updateMyPwd(Integer userId, String userPwd){return true;}
}
