package com.greenheart.ud.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@Getter
public class RedisUtil {

//    @Resource
//    private StringRedisTemplate redisTemplate;
//
//    private static Map<String,String> map=new HashMap<>();
//    public void setValue(String key, String value, long times, TimeUnit unit){
//        log.info("redis set key:{},value:{}",key,value);
//        map.put(key,value);
//    }
//
//    public String getValue(String key){
//        return map.remove(key);
//    }


}
