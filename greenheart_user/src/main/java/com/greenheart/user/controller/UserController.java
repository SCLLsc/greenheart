package com.greenheart.user.controller;

import com.greenheart.user.pojo.User;
import com.greenheart.user.service.UserService;
import com.greenheart.user.util.EmailUtil;
import com.greenheart.user.util.ObjectAndString;
import entity.JsonResult;
import entity.StatusCode;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@Slf4j
@Setter(onMethod_ = {@Autowired})
public class UserController {
    private UserService userService;
    private EmailUtil emailUtil;

    //注册获取验证码
//    @PostMapping("/user/sendCode/{email}/")
//    public JsonResult sendCode(@PathVariable String email){
//        if(userService.findByEmail(email)!=null){
//           return new JsonResult(false, StatusCode.ERROR,"邮箱已注册");
//        }else {
//            if(emailUtil.registerCode(email).equals("发送成功")){
//                return new JsonResult(true, StatusCode.SUCESS,"已发送验证码");
//            }else {
//                return new JsonResult(false, StatusCode.ERROR,"发送失败");
//            }
//        }
//    }

    //注册
//    @PostMapping("/user/register/")
//    public JsonResult register(@RequestBody ObjectAndString<User,String> register){
//        User user=register.getFirst();
//        String code=register.getSecond();
//        if(emailUtil.checkCode(user.getEmail(),code).equals("校验成功")){
//            if(userService.register(register.getFirst())){
//                return new JsonResult(true, StatusCode.SUCESS,"注册成功");
//            }else {
//                return new JsonResult(false, StatusCode.ERROR,"注册失败");
//            }
//        }else {
//               return new JsonResult(false, StatusCode.ERROR,"验证码错误");
//        }
//    }

    //登录
    @PostMapping("/user/login/{email}/{userPwd}/")
    public JsonResult login(@PathVariable String email,@PathVariable String userPwd){
        ObjectAndString<User,String> result=userService.login(email,userPwd);
        if(result.getFirst()!=null){
            return new JsonResult(true, StatusCode.SUCESS,result.getSecond(),result);
        }else {
            return new JsonResult(false, StatusCode.LOGINERROR,result.getSecond(),result);
        }
    }

    //登出
    @PostMapping("/user/logout/")
    public JsonResult logout(HttpSession session){
        session.removeAttribute("userId");
        return new JsonResult(false, StatusCode.SUCESS,"登出成功");
    }

    //注销
    @PostMapping("/user/cancellation/{userId}/")
    public JsonResult cancellation(@PathVariable Integer userId){
        userService.cancellation(userId);
        return new JsonResult(false, StatusCode.SUCESS,"注销成功");
    }
    //找回密码
    @PostMapping("/user/retrievepwd/{email}/")
    public JsonResult retrievePwd(@PathVariable String email){
        String retrievePwd=userService.retrievePwd(email);
        if(retrievePwd!=null){
            return new JsonResult(true, StatusCode.SUCESS,"找回成功",retrievePwd);
        }else {
            return new JsonResult(false, StatusCode.ERROR,"找回失败");
        }
    }


}
