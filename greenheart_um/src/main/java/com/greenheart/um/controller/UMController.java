package com.greenheart.um.controller;

import com.greenheart.um.client.*;
import com.greenheart.um.pojo.*;
import com.greenheart.um.service.ReplyService;
import com.greenheart.um.util.ObjectAndString;
import com.mysql.cj.protocol.x.Notice;
import entity.JsonResult;
import entity.StatusCode;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Slf4j
@Setter(onMethod_ = {@Autowired})
public class UMController {
    private ReplyService replyService;

    @Resource
    private DMClient dmClient;
    @Resource
    private NAAMClient naamClient;
    @Resource
    private PIMClient pimClient;
    @Resource
    private ProcessClient processClient;
    @Resource
    private UDClient udClient;
    @Resource
    private UserClient userClient;


    //-----------------------------------DM模块-----------------------------------
    //查看所有的资料
    @PostMapping("/um/dm/allinformation/{pageNum}/")
    public JsonResult allInformation(@PathVariable("pageNum") Integer pageNum){
        JsonResult result=dmClient.allInformation(pageNum);
        return result;
    }
    //搜索查看资料
    @PostMapping("/um/dm/alllikeinformation/{like}/")
    public JsonResult allLikeInformation(@PathVariable("like") String like){
        JsonResult result=dmClient.allLikeInformation(like);
        return result;
    }
    //查看资料
    @PostMapping("/um/dm/findinformationById/{informationId}/")
    public JsonResult findInformationBy(@PathVariable("informationId") Integer informationId){
        JsonResult result=dmClient.findInformationBy(informationId);
        return result;
    }
    //查看心理评测
    @PostMapping("/um/dm/selectalltrial/{pageNum}/")
    public JsonResult selectAllTrial(@PathVariable("pageNum") Integer pageNum){
        JsonResult result=dmClient.selectAllTrial(pageNum);
        return result;
    }
    //搜索查看心理评测
    @PostMapping("/um/dm/selectlikealltrial/{like}/")
    public JsonResult selectLikeAllTrial(@PathVariable("like") String like){
        JsonResult result=dmClient.selectLikeAllTrial(like);
        return result;
    }
    //查看心理评测试题
    @PostMapping("/um/dm/selecttrialbytitle/{trialTitle}/{pageNum}/")
    public JsonResult selectTrialByTitle(@PathVariable("trialTitle") String trialTitle,@PathVariable("pageNum") Integer pageNum){
        JsonResult result=dmClient.selectTrialByTitle(trialTitle, pageNum);
        return result;
    }
    //搜索查看心理评测试题
    @PostMapping("/um/dm/selectliketrialbytitle/{trialTitle}/{like}/")
    public JsonResult selectLikeTrialByTitle(@PathVariable("trialTitle") String trialTitle,@PathVariable("like") String like){
        JsonResult result=dmClient.selectLikeTrialByTitle(trialTitle,like);
        return result;
    }
    //查看单个心理评测试题
    @PostMapping("/um/dm/selecttrialbyid/{trialId}/")
    public JsonResult selectTrialById(@PathVariable("trialId") Integer trialId){
        JsonResult result=dmClient.selectTrialById(trialId);
        return result;
    }
    //增加单个心理评测题目
    @PostMapping("/um/dm/addtrialone/{trialNum}/")
    public JsonResult addTrialOne(@PathVariable Integer trialNum,@RequestBody Trial trial){
        JsonResult result=dmClient.addTrialOne(trialNum,trial);
        return result;
    }
    //增加心理评测
    @PostMapping("/um/dm/addtrial/{userId}/")
    public JsonResult addTrial(@PathVariable Integer userId,@RequestBody Trial trial){
        JsonResult result=dmClient.addTrial(userId,trial);
        return result;
    }
    //删除单个心理评测
    @PostMapping("/um/dm/removetrialbyid/{trialId}/")
    public JsonResult removeTrialById(@PathVariable("trialId") Integer trialId){
        JsonResult result=dmClient.removeTrialById(trialId);
        return result;
    }

    //删除心理评测
    @PostMapping("/um/dm/removetrial/{trialTitle}/")
    public JsonResult removeTrial(@PathVariable("trialTitle") String trialTitle){
        JsonResult result=dmClient.removeTrial(trialTitle);
        return result;
    }

    //修改心理评测
    @PostMapping("/um/dm/updatetrial/")
    public JsonResult updateTrial(@RequestBody Trial trial){
        JsonResult result=dmClient.updateTrial(trial);
        return result;
    }

    //修改心理评测时间
    @PostMapping("/um/dm/updatetrialcycle/")
    public JsonResult updateTrialCycle(@RequestBody Trial trial){
        JsonResult result=dmClient.updateTrialCycle(trial);
        return result;
    }
    //修改Excel心理评测
    @PostMapping("/dm/updatealltrial/")
    public JsonResult updateAllTrial(@RequestBody List<Trial> trial){
        JsonResult result=dmClient.updateAllTrial(trial);
        return result;
    }
    //-----------------------------------NAAM模块-----------------------------------
    //查看公告
    @PostMapping("/um/naam/allnotice/")
    public JsonResult allNotice(){
        JsonResult result=naamClient.allNotice();
        return result;
    }
    //查看单个公告
    @PostMapping("/um/naam/onenotice/{noticeId}/")
    public JsonResult oneNotice(@PathVariable("noticeId") Integer noticeId){
        JsonResult result=naamClient.oneNotice(noticeId);
        return result;
    }

    //新增公告
    @PostMapping("/um/naam/addnotice/")
    public JsonResult addNotice(@RequestBody Notice notice){
        JsonResult result=naamClient.addNotice(notice);
        return result;
    }

    //修改公告
    @PostMapping("/um/naam/updatenotice/")
    public JsonResult updateNotice(@RequestBody Notice notice){
        JsonResult result=naamClient.updateNotice(notice);
        return result;
    }

    //删除公告
    @PostMapping("/um/naam/removenotice/{noticeId}/")
    public JsonResult removeNotice(@PathVariable("noticeId") Integer noticeId){
        JsonResult result=naamClient.removeNotice(noticeId);
        return result;
    }
    //---------------------------------PIM模块-----------------------------------
    //个人信息查看
    @PostMapping("/um/pim/myself/{userId}")
    public JsonResult myself(@PathVariable("userId") String userId){
        JsonResult result=pimClient.myself(userId);
        return result;
    }
    // 删除上传的资料
    @PostMapping("/um/pim/removeinformation/{informationId}/")
    public JsonResult removeInformation(@PathVariable("informationId") String informationId){
        JsonResult result=pimClient.removeInformation(informationId);
        return result;
    }
    //---------------------------------Process模块-----------------------------------
    //查看所有待审核资料
    @PostMapping("/um/process/allNoInformation/{pageNum}/")
    public JsonResult allNoInformation(@PathVariable("pageNum") Integer pageNum){
        JsonResult result=processClient.allNoInformation(pageNum);
        return result;
    }

    //搜索待审核资料
    @PostMapping("/um/process/allLikeNoInformation/{like}/")
    public JsonResult allLikeNoInformation(@PathVariable("like") String like){
        JsonResult result=processClient.allLikeNoInformation(like);
        return result;
    }

    //审核资料
    @PostMapping("/um/process/{informationId}/{process}/")
    public JsonResult process(@PathVariable("informationId") Integer informationId,@PathVariable("process") Integer process){
        JsonResult result=processClient.process(informationId,process);
        return result;
    }

    //---------------------------------UD模块-----------------------------------
    //上传资料
    @PostMapping("/um/ud/information/upload/")
    public JsonResult uploadInformation(@RequestBody Information information){
        JsonResult result=udClient.uploadInformation(information);
        return result;
    }
    //---------------------------------User模块-----------------------------------
    //登录
    @PostMapping("/um/user/login/{email}/{userPwd}/")
    public JsonResult login(@PathVariable String email, @PathVariable String userPwd, HttpSession session){
        JsonResult result=userClient.login(email, userPwd, session);
        return result;
    }

    //登出
    @PostMapping("/um/user/logout/")
    public JsonResult logout(HttpSession session){
        JsonResult result=userClient.logout(session);
        return result;
    }
    //---------------------------------本模块-----------------------------------
    //查看所有用户
    @PostMapping("/um/viewalluser/{pageNum}/")
    public JsonResult viewAllUser(@PathVariable Integer pageNum){
        ObjectAndString<List<User>,Integer> users= replyService.viewAllUser(pageNum);
        if(users!=null){
            return new JsonResult(true, StatusCode.SUCESS,"查找成功",users);
        }else {
            return new JsonResult(false, StatusCode.ERROR,"查找失败");
        }
    }
    //查看所有管理员
    @PostMapping("/um/viewallmduser/{pageNum}/")
    public JsonResult viewMdAllUser(@PathVariable Integer pageNum){
        ObjectAndString<List<User>,Integer> users= replyService.viewMdAllUser(pageNum);
        if(users!=null){
            return new JsonResult(true, StatusCode.SUCESS,"查找成功",users);
        }else {
            return new JsonResult(false, StatusCode.ERROR,"查找失败");
        }
    }
    //条件查询用户
    @PostMapping("/um/viewlikeuser/{like}/")
    public JsonResult viewLikeUser(@PathVariable String like){
        ObjectAndString<List<User>,Integer> users= replyService.viewLikeUser(like);
        if(users!=null){
            return new JsonResult(true, StatusCode.SUCESS,"查找成功",users);
        }else {
            return new JsonResult(false, StatusCode.ERROR,"查找失败");
        }
    }
    //条件查询管理员
    @PostMapping("/um/viewlikemduser/{like}/")
    public JsonResult viewLikeMdUser(@PathVariable String like){
        ObjectAndString<List<User>,Integer> users= replyService.viewLikeMdUser(like);
        if(users!=null){
            return new JsonResult(true, StatusCode.SUCESS,"查找成功",users);
        }else {
            return new JsonResult(false, StatusCode.ERROR,"查找失败");
        }
    }
    //查看咨询(待回复)
    @PostMapping("/um/viewconsultation/{guidanceStatus}/{pageNum}/")
    public JsonResult viewConsultation(@PathVariable Integer guidanceStatus,@PathVariable Integer pageNum){
        ObjectAndString<List<Guidance>,Integer> guidances= replyService.viewConsultation(guidanceStatus,pageNum);
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",guidances);
    }
    //条件查询用户咨询
    @PostMapping("/um/viewlikeconsultation/{guidanceStatus}/{like}/")
    public JsonResult viewLikeConsultation(@PathVariable Integer guidanceStatus,@PathVariable String like){
        ObjectAndString<List<Guidance>,Integer> users= replyService.viewLikeConsultation(guidanceStatus,like);
        if(users!=null){
            return new JsonResult(true, StatusCode.SUCESS,"查找成功",users);
        }else {
            return new JsonResult(false, StatusCode.ERROR,"查找失败");
        }
    }
    //查看单个咨询未回复内容
    @PostMapping("/um/viewoneconsultation/{guidanceId}/")
    public JsonResult viewOneConsultation(@PathVariable Integer guidanceId){
        Guidance guidance= replyService.viewOneConsultation(guidanceId);
        return new JsonResult(true, StatusCode.SUCESS,"查找成功",guidance);
    }
    //心理咨询回复
    @PostMapping("/um/pcreply/")
    public JsonResult pcReply(@RequestBody Reply reply){
        if(replyService.pcReply(reply)){
            return new JsonResult(true, StatusCode.SUCESS,"回复成功");
        }else {
            return new JsonResult(false, StatusCode.ERROR,"回复失败");
        }
    }

    //删除用户
    @PostMapping("/um/removeuser/{userId}/")
    public JsonResult removeUser(@PathVariable Integer userId){
        replyService.cancellation(userId);
        return new JsonResult(true, StatusCode.SUCESS,"注销成功");
    }
    //修改用户状态
    @PostMapping("um/edituserstatus/{userId}/{role}/")
    public JsonResult editUserStatus(@PathVariable Integer userId,@PathVariable Integer role){
        replyService.editUserStatus(userId,role);
        return new JsonResult(true, StatusCode.SUCESS,"修改成功");
    }
    //给用户发通知
    @PostMapping("um/getusernotice/")
    public JsonResult getUserNotice(@RequestBody Guidance guidance){
        replyService.getUserNotice(guidance);
        return new JsonResult(true, StatusCode.SUCESS,"发送成功");
    }

}
