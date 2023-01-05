package com.greenheart.pim.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.greenheart.pim.dao.GuidanceMapper;
import com.greenheart.pim.pojo.Guidance;
import com.greenheart.pim.service.GuidanceService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
@Slf4j
@Setter(onMethod_={@Resource} )
public class GuidanceServiceImpl extends ServiceImpl<GuidanceMapper, Guidance> implements GuidanceService {

}
