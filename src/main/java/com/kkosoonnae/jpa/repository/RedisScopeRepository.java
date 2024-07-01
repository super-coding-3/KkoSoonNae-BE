package com.kkosoonnae.jpa.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.text.DecimalFormat;


@Repository
public class RedisScopeRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisScopeRepository(RedisTemplate<String, String> redisTemplate)  {
        this.redisTemplate = redisTemplate;
    }

    public void addScope(Integer cstmrNo,Integer storeNo, double scope) {
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
                    .get(generateScopeKey(storeNo) , "totalScope");
            Object scopeCountObj = redisTemplate
                    .opsForHash()
                    .get(generateScopeKey(storeNo) , "scopeCount");

            if (totalScopeObj == null || scopeCountObj == null) {
                return 0.0;
            }
            double totalScope = Double.parseDouble((String) totalScopeObj);
            int scopeCount =Integer.parseInt((String) scopeCountObj);

            if (scopeCount == 0) {
                return 0.0;
            }

            double averageScope = totalScope / scopeCount;

            DecimalFormat decimalFormat = new DecimalFormat("0.0");
            return Double.parseDouble(decimalFormat.format(averageScope));

        } catch (Exception e) {
            return 0.0;
        }
    }
    public void deleteScope(Integer cstmrNo, Integer storeNo) {
        redisTemplate.opsForHash().delete(generateScopeKey(storeNo), "totalScope");
        redisTemplate.opsForHash().delete(generateScopeKey(storeNo), "scopeCount");
    }

    private String generateScopeKey(Integer storeNo) {
        return "store:" + storeNo + ":scope";
    }
}
