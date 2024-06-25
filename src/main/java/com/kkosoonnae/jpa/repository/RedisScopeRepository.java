package com.kkosoonnae.jpa.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Repository
public class RedisScopeRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisScopeRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void addScope(Integer storeNo, double scope) {
        redisTemplate
                .opsForHash()
                .increment(generateScopeKey(storeNo), "totalScope", scope);
        redisTemplate
                .opsForHash()
                .increment(generateScopeKey(storeNo), "scopeCount", 1);
    }
    public double getAverageScope(Integer storeNo) {
        try {
            Object totalScopeObj = redisTemplate
                    .opsForHash()
                    .get(generateScopeKey(storeNo), "totalScope");
            Object scopeCountObj = redisTemplate
                    .opsForHash()
                    .get(generateScopeKey(storeNo), "scopeCount");

            if (totalScopeObj == null || scopeCountObj == null) {
                return 0.0;
            }
            double totalScope = (double) totalScopeObj;
            int scopeCount = (int) scopeCountObj;

            if (scopeCount == 0) {
                return 0.0;
            }

            return totalScope / scopeCount;

        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }
    private String generateScopeKey(Integer storeNo) {
        return "store:" + storeNo + ":scope";
    }
}
