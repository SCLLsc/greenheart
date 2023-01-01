package com.greenheart.pc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.greenheart.pc.pojo.Guidance;
import com.greenheart.pc.util.ObjectAndString;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface GuidanceService extends IService<Guidance> {

    default boolean consult(Integer userId,String guidanceContent){return false;}

    default ObjectAndString<List<Guidance>,Integer> viewConsultation(Integer userId, Integer guidanceStatus, Integer pageNum){return null;}

    default boolean delGuidance(Integer guidanceId){return false;}

    default ObjectAndString<List<Guidance>, Integer> viewLikeConsultation(Integer userId, Integer guidanceStatus, String like){return null;}
}
