package com.kkosoonnae.jpa.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public class RedisLikeStoreRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisLikeStoreRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Long LikeStoreCount(Integer cstmrNo, Integer storeNo) {
        Long added = redisTemplate
                .opsForSet()
                .add(generateScopeKey(cstmrNo,storeNo),String.valueOf(storeNo));
        return added != null ? added : 0L;
    }

    public Long getTotalLikeStoreCount(Integer storeNo) {
        String keyPattern = generateInterestSetKeyPattern(storeNo);
        Set<String> keys = redisTemplate.keys(keyPattern);
        long totalCount = 0L;
        for (String key : keys) {
            if (redisTemplate.opsForSet().isMember(key, String.valueOf(storeNo))) {
                totalCount++;
            }
        }
        return totalCount;
    }
        public void RedisDeleteLikeStore(Integer cstmrNo, Integer storeNo) {
            redisTemplate.
                    opsForSet()
                    .remove(generateScopeKey(cstmrNo, storeNo), String.valueOf(storeNo));
        }


    private String generateInterestSetKeyPattern(Integer storeNo) {
        return "store_interest:" + storeNo + ":user:*";
    }

    private String generateScopeKey(Integer cstmrNo, Integer storeNo) {
        return "scope:" + cstmrNo + ":" + storeNo;
    }
}
