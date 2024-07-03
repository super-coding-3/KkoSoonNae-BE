package com.kkosoonnae.jpa.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisNoticeRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisNoticeRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


}
