package com.greenheart.naam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.greenheart.naam.pojo.Notice;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface NoticeService extends IService<Notice> {
    default List<Notice> allNotice(){return null;}

    default boolean addNotice(String noticeTitle,String noticeContent){return false;}

    default boolean updateNotice(Integer noticeId,String noticeTitle,String noticeContent){return false;}

}
