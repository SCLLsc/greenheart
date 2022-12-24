package com.greenheart.pe.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.greenheart.pe.pojo.Mark;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkService extends IService<Mark> {
    default List<Mark> viewScore(Integer userId){return null;}
}
