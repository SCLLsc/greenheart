package com.greenheart.pc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.greenheart.pc.pojo.Guidance;

import java.util.List;

public interface GuidanceService extends IService<Guidance> {

    default boolean consult(Guidance guidance){return false;}

    default List<Guidance> viewConsultation(Integer userId,Integer guidanceStatus,Integer pageNum){return null;}
}
