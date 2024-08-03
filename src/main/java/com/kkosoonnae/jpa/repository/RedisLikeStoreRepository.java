package com.kkosoonnae.jpa.repository;

import org.springframework.data.redis.RedisConnectionFailureException;
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
        String key = generateScopeKey(cstmrNo, storeNo);
        try {
            Long added = redisTemplate
                    .opsForSet()
                    .add(key, String.valueOf(cstmrNo));
            return added != null ? added : 0L;
        } catch (RedisConnectionFailureException e) {
            e.printStackTrace();
            return 0L;
        }
    }
    public Long getTotalLikeStoreCount(Integer storeNo) {
        String keyPattern = generateInterestSetKeyPattern(storeNo);
        Set<String> keys = redisTemplate.keys(keyPattern);
        long totalCount = 0L;
        for (String key : keys) {
            Long setsize = redisTemplate.opsForSet().size(key);
            if (setsize != null && setsize > 0) {
                totalCount++;
            }
        }
        return totalCount;
    }
        public void RedisDeleteLikeStore(Integer cstmrNo, Integer storeNo) {
            redisTemplate.
                    opsForSet()
                    .remove(generateScopeKey(cstmrNo, storeNo), String.valueOf(cstmrNo));
        }


    private String generateInterestSetKeyPattern(Integer storeNo) {
        return "like:store:" + storeNo + ":user:*";
    }
    private String generateScopeSetKey(Integer storeNo) {
        return "like:store:" + storeNo;
    }

    private String generateScopeKey(Integer cstmrNo, Integer storeNo) {
        return "like:store:" +storeNo +":user:" + cstmrNo;
    }
}
