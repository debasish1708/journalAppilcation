package com.deba1708.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Disabled
    @Test
    public void testRedisTemplate() {
//        redisTemplate.opsForValue().set("email", "email@email.com");
        Object name = redisTemplate.opsForValue().get("email");
        System.out.println(name);
    }
}
