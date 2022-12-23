package com.greenheart.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.greenheart.user.pojo.User;
import com.greenheart.user.util.ObjectAndString;

public interface UserService extends IService<User>{

    default boolean register(User user){return false;}

    default ObjectAndString<User,String> login(String email, String userPwd){return null;}

    default User findByEmail(String email){return null;}

    default String retrievePwd(String email){return null;}

    default boolean cancellation(Integer userId){return false;}

}
