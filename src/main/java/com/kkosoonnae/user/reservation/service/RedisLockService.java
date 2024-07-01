package com.kkosoonnae.user.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisLockService {
    private static final String LOCK_KEY_PREFIX = "LOCK_";
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisLockService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean lock(String key, long expireTime) {
        Boolean success = redisTemplate.opsForValue().setIfAbsent(LOCK_KEY_PREFIX + key, "LOCK", expireTime, TimeUnit.MILLISECONDS);
        return success != null && success;
    }

    public void unlock(String key) {
        redisTemplate.delete(LOCK_KEY_PREFIX + key);
    }
}
