package com.kkosoonnae.jpa.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import static org.springframework.cache.interceptor.SimpleKeyGenerator.generateKey;
@Repository
public class RedisLikeStoreRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisLikeStoreRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Long LikeStoreCount(Integer storeNo) {
        return redisTemplate
                .opsForValue()
                .increment(generateInterestKey(storeNo));
    }

    public Long getTotalLikeStoreCount(Integer storeNo) {
        String count = redisTemplate
                .opsForValue()
                .get(generateInterestKey(storeNo));
        try {
            return count != null ? Long.parseLong(count) : 0L;
        } catch (NumberFormatException e) {
            return 0L;
        }
    }

    private String generateInterestKey(Integer storeNo) {
        return "store:interest:" + storeNo;
    }
}
