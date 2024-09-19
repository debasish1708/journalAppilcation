package com.deba1708.journalApp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisService {

    private final RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> entityClass){
        try{
            Object o = redisTemplate.opsForValue().get(key);
            // mapper is used to map the object o.toString() to pojo class
            ObjectMapper mapper = new ObjectMapper();
            // mapper.readValue is used to read the Object values by the pojo class get return in the pojo class
            assert o != null;
            return mapper.readValue(o.toString(),entityClass);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public void set(String key, Object o,Long ttl){
        try{
            String jsonValue = new ObjectMapper().writeValueAsString(o);
            redisTemplate.opsForValue().set(key,jsonValue,ttl, TimeUnit.SECONDS);
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }

}
