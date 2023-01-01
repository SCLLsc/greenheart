package com.greenheart.um.client;

import com.greenheart.um.breaker.NAAMBreaker;
import entity.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "greenheartnaam",fallback = NAAMBreaker.class)
public interface NAAMClient {
    //查看公告
    @PostMapping("/naam/allnotice/")
    public JsonResult allNotice();

    //查看单个公告
    @PostMapping("/naam/onenotice/{noticeId}/")
    public JsonResult oneNotice(@PathVariable("noticeId") Integer noticeId);

    //新增公告
    @PostMapping("/naam/addnotice/{noticeTitle}/{noticeContent}/")
    public JsonResult addNotice(@PathVariable("noticeTitle") String noticeTitle,@PathVariable("noticeContent") String noticeContent);

    //修改公告
    @PostMapping("/naam/updatenotice/{noticeId}/{noticeTitle}/{noticeContent}/")
    public JsonResult updateNotice(@PathVariable("noticeId") Integer noticeId,@PathVariable("noticeTitle") String noticeTitle,@PathVariable("noticeContent") String noticeContent);

    //删除公告
    @PostMapping("/naam/removenotice/{noticeId}/")
    public JsonResult removeNotice(@PathVariable("noticeId") Integer noticeId);
}
