package com.greenheart.pe.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.greenheart.pe.pojo.Mark;
import com.greenheart.pe.util.ObjectAndString;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkService extends IService<Mark> {
    default ObjectAndString<List<Mark>,Integer> viewScore(Integer userId, Integer pageNum){return null;}

    default ObjectAndString<List<Mark>,Integer> viewLikeScore(Integer userId, String like){return null;}
}
