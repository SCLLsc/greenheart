package com.greenheart.user.controller;

import cn.hutool.core.lang.UUID;
import com.greenheart.user.client.*;
import com.greenheart.user.pojo.Guidance;
import com.greenheart.user.pojo.Information;
import com.greenheart.user.pojo.User;
import com.greenheart.user.service.UserService;
import com.greenheart.user.util.EmailUtil;
import com.greenheart.user.util.MD5Util;
import com.greenheart.user.util.ObjectAndString;
import entity.JsonResult;
import entity.StatusCode;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;



@RestController
@Slf4j
@Setter(onMethod_ = {@Autowired})
public class UserController {
    private UserService userService;
    private EmailUtil emailUtil;

    @Resource
    private CollectClient collectClient;
    @Resource
    private DMClient dmClient;
    @Resource
    private NAAMClient naamClient;
    @Resource
    private PCClient pcClient;
    @Resource
    private PEClient peClient;
    @Resource
    private PIMClient pimClient;
    @Resource
    private UDClient udClient;
    @Resource
    private UMClient umClient;



//-----------------------------------Collect模块-----------------------------------
    //调用查看收藏功能
    @PostMapping("/user/collect/{userId}/{pageNum}/")
    public JsonResult selectAllCollect(@PathVariable("userId") Integer userId, @PathVariable("pageNum") Integer pageNum){
        JsonResult result=collectClient.selectAllCollect(userId, pageNum);
        return result;
    }
    //搜索查看收藏
    @PostMapping("/user/collect/selectlikeallcollect/{userId}/{like}/")
    public JsonResult selectLikeAllCollect(@PathVariable("userId")  Integer userId,@PathVariable("like")  String like){
        JsonResult result=collectClient.selectLikeAllCollect(userId,like);
        return result;
    }

    //调用收藏资料
    @PostMapping("/user/collect/addcollect/{userId}/{informationId}/")
    public JsonResult addCollect(@PathVariable("userId") Integer userId,@PathVariable("informationId") Integer informationId){
        JsonResult result=collectClient.addCollect(userId, informationId);
        return result;
    }
    //调用取消收藏
    @PostMapping("/user/collect/removecollect/{userId}/{informationId}/")
    public JsonResult removeById(@PathVariable("userId") Integer userId,@PathVariable("informationId") Integer informationId){
        JsonResult result=collectClient.removeById(userId,informationId);
        return result;
    }
//-----------------------------------DM模块-----------------------------------
    //查看所有的资料
    @PostMapping("/user/dm/allinformation/{pageNum}/")
    public JsonResult allInformation(@PathVariable("pageNum") Integer pageNum){
        JsonResult result=dmClient.allInformation(pageNum);
        return result;
    }
    //搜索查看资料
    @PostMapping("/user/dm/alllikeinformation/{like}/")
    public JsonResult allLikeInformation(@PathVariable("like") String like){
        JsonResult result=dmClient.allLikeInformation(like);
        return result;
    }
    //查看资料
    @PostMapping("/user/dm/findinformationById/{informationId}/")
    public JsonResult findInformationBy(@PathVariable("informationId") Integer informationId){
        JsonResult result=dmClient.findInformationBy(informationId);
        return result;
    }
    //查看心理评测
    @PostMapping("/user/dm/selectalltrial/{pageNum}/")
    public JsonResult selectAllTrial(@PathVariable("pageNum") Integer pageNum){
        JsonResult result=dmClient.selectAllTrial(pageNum);
        return result;
    }
    //搜索查看心理评测
    @PostMapping("/user/dm/selectlikealltrial/{like}/")
    public JsonResult selectLikeAllTrial(@PathVariable("like") String like){
        JsonResult result=dmClient.selectLikeAllTrial(like);
        return result;
    }
    //开始做心理评测试题
    @PostMapping("/user/dm/starttrialbytitle/{trialTitle}/")
    public JsonResult startTrialByTitle(@PathVariable("trialTitle") String trialTitle){
        JsonResult result=dmClient.startTrialByTitle(trialTitle);
        return result;
    }
//-----------------------------------NAAM模块-----------------------------------
    //查看公告
    @PostMapping("/user/naam/allnotice/")
    public JsonResult allNotice(){
        JsonResult result=naamClient.allNotice();
        return result;
    }
//-----------------------------------PC模块-----------------------------------
    //心理咨询
    @PostMapping("/user/pc/consult/")
    public JsonResult consult(@RequestBody Guidance guidance){
        JsonResult result=pcClient.consult(guidance);
        return result;
    }
    //查看心理咨询回复
    @PostMapping("/user/pc/viewreply/{guidanceId}")
    public JsonResult viewReply(@PathVariable("guidanceId") Integer guidanceId){
       JsonResult result=pcClient.viewReply(guidanceId);
       return result;
    }
    //查看咨询(待回复|已回复)
    @PostMapping("/user/pc/viewconsultation/{userId}/{guidanceStatus}/{pageNum}/")
    public JsonResult viewConsultation(@PathVariable("userId") Integer userId,@PathVariable("guidanceStatus") Integer guidanceStatus,@PathVariable("pageNum") Integer pageNum){
        JsonResult result=pcClient.viewConsultation(userId, guidanceStatus, pageNum);
        return result;
    }
    //搜索查看咨询(待回复|已回复)
    @PostMapping("/user/pc/viewlikeconsultation/{userId}/{guidanceStatus}/{like}/")
    public JsonResult viewLikeConsultation(@PathVariable("userId") Integer userId,@PathVariable("guidanceStatus") Integer guidanceStatus,@PathVariable("like") String like){
        JsonResult result=pcClient.viewLikeConsultation(userId, guidanceStatus, like);
        return result;
    }
    //取消咨询
    @PostMapping("/user/pc/removeguidance/{guidanceId}")
    public JsonResult removeGuidance(@PathVariable("guidanceId") Integer guidanceId){
        JsonResult result=pcClient.removeGuidance(guidanceId);
        return result;
    }
    //删除已回复的咨询
    @PostMapping("/user/pc/deleteguidance/{guidanceId}/")
    public JsonResult delGuidance(@PathVariable("guidanceId") Integer guidanceId){
        JsonResult result=pcClient.delGuidance(guidanceId);
        return result;
    }
//---------------------------------PE模块-----------------------------------
    //心理评测
    @PostMapping("/user/pe/evaluating/{userId}/{trialTitle}/{markScore}/")
    public JsonResult evaluating(@PathVariable("userId") Integer userId, @PathVariable("trialTitle") String trialTitle, @PathVariable("markScore") Integer markScore){
        JsonResult result=peClient.evaluating(userId, trialTitle, markScore);
        return result;
    }
    //查看成绩
    @PostMapping("/user/pe/viewscore/{userId}/{pageNum}/")
    public JsonResult viewScore(@PathVariable("userId") Integer userId,@PathVariable("pageNum") Integer pageNum){
        JsonResult result=peClient.viewScore(userId,pageNum);
        return result;
    }
    //搜索查看成绩
    @PostMapping("/user/pe/viewlikescore/{userId}/{like}/")
    public JsonResult viewLikeScore(@PathVariable("userId") Integer userId,@PathVariable("like") String like){
        JsonResult result=peClient.viewLikeScore(userId,like);
        return result;
    }
    //删除成绩
    @PostMapping("/user/pe/delscore/{markId}/")
    public JsonResult delScore(@PathVariable("markId") Integer markId){
        JsonResult result=peClient.delScore(markId);
        return result;
    }
//---------------------------------PIM模块-----------------------------------
    //个人信息查看
    @PostMapping("/user/pim/myself/{userId}/")
    public JsonResult myself(@PathVariable("userId") String userId){
        JsonResult result=pimClient.myself(userId);
        return result;
    }

    //搜索查看上传的资料
    @PostMapping("/user/pim/mylikeinformation/{userId}/{informationStatus}/{like}/")
    public JsonResult myLikeInformation(@PathVariable("userId") String userId,@PathVariable("informationStatus") Integer informationStatus,@PathVariable("like") String like){
        JsonResult result=pimClient.myLikeInformation(userId, informationStatus, like);
        return result;
    }

    // 修改个人信息
    @PostMapping("/user/pim/updatemyself/")
    public JsonResult updateMyself(@RequestBody User user){
        JsonResult result=pimClient.updateMyself(user);
        return result;
    }

    // 修改密码
    @PostMapping("/user/pim/updatemypwd/")
    public JsonResult updateMyPwd(@RequestBody User user){
        JsonResult result=pimClient.updateMyPwd(user);
        return result;
    }

    // 查看上传的资料
    @PostMapping("/user/pim/allinformation/{userId}/{informationStatus}/{pageNum}/")
    public JsonResult myInformation(@PathVariable("userId") String userId,@PathVariable("informationStatus") Integer informationStatus,@PathVariable("pageNum") Integer pageNum){
        JsonResult result=pimClient.myInformation(userId, informationStatus, pageNum);
        return result;
    }

    // 删除上传的资料
    @PostMapping("/user/pim/removeinformation/{informationId}/")
    public JsonResult removeInformation(@PathVariable("informationId") String informationId){
        JsonResult result=pimClient.removeInformation(informationId);
        return result;
    }

    //查看通知
    @PostMapping("/pim/viewmyconsultation/{userId}/{guidanceStatus}/{pageNum}/")
    public JsonResult viewMyConsultation(@PathVariable("userId") Integer userId,@PathVariable("guidanceStatus") Integer guidanceStatus,@PathVariable("pageNum") Integer pageNum){
        JsonResult result=pimClient.viewMyConsultation(userId, guidanceStatus, pageNum);
        return result;
    }

    //删除通知
    @PostMapping("/pim/delmyconsultation/{guidanceId}/")
    public JsonResult delConsultation(@PathVariable("userId") Integer guidanceId){
        JsonResult result=pimClient.delConsultation(guidanceId);
        return result;
    }

    //条件查询用户咨询
    @PostMapping("/pim/viewlikemyconsultation/{userId}/{guidanceStatus}/{like}/")
    public JsonResult viewLikeMyConsultation(@PathVariable("userId") Integer userId,@PathVariable("guidanceStatus") Integer guidanceStatus,@PathVariable("like") String like){
        JsonResult result=pimClient.viewLikeMyConsultation(userId, guidanceStatus, like);
        return result;
    }
//---------------------------------UD模块-----------------------------------
    //上传资料
    @PostMapping("/user/ud/information/upload/")
    public JsonResult uploadInformation(@RequestBody Information information){
        JsonResult result=udClient.uploadInformation(  information);
        return result;
    }
//---------------------------------UM模块-----------------------------------
    //删除用户
    @PostMapping("/um/removeuser/{userId}/")
    public JsonResult removeUser(@PathVariable("userId") Integer userId){
        JsonResult result=umClient.removeUser(userId);
        return result;
    }
//---------------------------------本模块-----------------------------------
    //注册获取验证码
    @PostMapping("/user/sendCode/{email}/")
    public JsonResult sendCode(@PathVariable String email){
        if(userService.findByEmail(email)!=null){
           return new JsonResult(false, StatusCode.ERROR,"邮箱已注册");
        }else {
            String code=emailUtil.registerCode(email);
            if(code!=null){
                return new JsonResult(true, StatusCode.SUCESS,"已发送验证码",code);
            }else {
                return new JsonResult(false, StatusCode.ERROR,"发送失败");
            }
        }
    }

    //注册
    @PostMapping("/user/register/{email}/{userPwd}/{code}/")
    public JsonResult register(@PathVariable String email,@PathVariable String userPwd,@PathVariable String code){
        User user=new User();
        user.setEmail(email);
        user.setUserName("使用者");
        user.setUserPwd(userPwd);
        if(emailUtil.checkCode(email,code).equals("验证成功")){
            if(userService.register(user)){
                return new JsonResult(true, StatusCode.SUCESS,"注册成功");
            }else {
                return new JsonResult(false, StatusCode.ERROR,"注册失败");
            }
        }else {
               return new JsonResult(false, StatusCode.ERROR,"验证码错误");
        }
    }

    //登录
    @PostMapping("/user/login/{email}/{userPwd}/")
    public JsonResult login(@PathVariable String email,@PathVariable String userPwd,HttpSession session){
        ObjectAndString<User,String> result=userService.login(email,userPwd);
        if(result.getFirst()!=null){
            String token= UUID.randomUUID().toString().replace("*",".").toLowerCase();
            session.getServletContext().setAttribute(token,result.getFirst());
            result.setSecond(token);
            return new JsonResult(true, StatusCode.SUCESS,"登录成功",result);
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
        User user=userService.findByEmail(email);
        if(user!=null){
            MD5Util md5=new MD5Util();
            String userPwd=md5.convertMD5(user.getUserPwd());
            String pwd=emailUtil.registerPwd(email,userPwd);
            return new JsonResult(true, StatusCode.SUCESS,"找回成功,已发送邮件",pwd);
        }else {
            return new JsonResult(false, StatusCode.ERROR,"邮箱未注册");
        }
    }


}
