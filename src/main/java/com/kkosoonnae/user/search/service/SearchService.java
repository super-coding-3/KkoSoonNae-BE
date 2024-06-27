package com.kkosoonnae.user.search.service;

import com.kkosoonnae.common.exception.CustomException;
import com.kkosoonnae.common.exception.ErrorCode;
import com.kkosoonnae.jpa.entity.Review;
import com.kkosoonnae.jpa.entity.Store;
import com.kkosoonnae.jpa.projection.MainStoresListviewProjection;
import com.kkosoonnae.jpa.repository.*;
import com.kkosoonnae.user.search.dto.MainStoreListViewResponseDto;
import com.kkosoonnae.user.search.dto.StoreListViewResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.kkosoonnae.jpa.entity.QStore.store;


/**
 * packageName    : com.kkosoonnae.store.service
 * fileName       : searchService
 * author         : KimJaeIk
 * date           : 2024-05-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-22        KimJaeIk    최초 생성
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final RedisLikeStoreRepository redisLikeStoreRepository;
    private final RedisScopeRepository redisScopeRepository;
    private final StoreQueryRepository storeQueryRepository;

    public List<StoreListViewResponseDto> findByStores(String nameAddressKeyword) {
        try {
           List<StoreListViewResponseDto> stores= storeQueryRepository.findStoreListQuery(nameAddressKeyword);
            if (stores.isEmpty()) {
                throw new CustomException(ErrorCode.STORE_NOT_FOUND);
            }
            return stores.stream()
                    .map(storeList -> {
                        double averageScope = redisScopeRepository.getAverageScope(storeList.getStoreNo());
                        Long likeStore = redisLikeStoreRepository.getTotalLikeStoreCount(storeList.getStoreNo());
                        log.info("Retrieved from Redis - storeNo: {}, averageScope: {},likeStore: {}",
                                storeList.getStoreNo(),averageScope,likeStore);
                        return  storeList.ListToDto(averageScope,likeStore);
                    })
                    .collect(Collectors.toList());
        } catch (DataAccessException dae) {
            log.error("Database access error", dae);
            throw new CustomException(ErrorCode.DATABASE_ERROR);
        }
    }

    public List<MainStoreListViewResponseDto> findByMainStores(String addressKeyword, Pageable pageable) {
        try {
            List<MainStoreListViewResponseDto> mainStoreResponses = storeQueryRepository.listViewResponseDto(addressKeyword,pageable);
            if (mainStoreResponses.isEmpty()) {
                throw new CustomException(ErrorCode.STORE_NOT_FOUND);
            }
            Collections.shuffle(mainStoreResponses);

            return mainStoreResponses.stream()
                    .map(mainStores -> {
                        double averageScope = redisScopeRepository.getAverageScope(mainStores.getStoreNo());
                        Long totalLike = redisLikeStoreRepository.getTotalLikeStoreCount(mainStores.getStoreNo());
                        log.info("Retrieved from Redis - storeNo: {}, averageScope: {}, totalLike: {}",
                                mainStores.getStoreNo(),averageScope,totalLike);
                       return mainStores.mainStoreToDto(totalLike,averageScope);
                    })
                    .collect(Collectors.toList());
        } catch (DataAccessException dae) {
            log.error("Data access exception occurred: " + dae.getMessage(), dae);
            throw new CustomException(ErrorCode.DATABASE_ERROR);
        }
    }

    public double getAverageReviewScore(Integer storeId){
        List<Review> reviews = reviewRepository.findByStoreStoreNo(storeId);
        double averageScore= reviews.stream()
                .mapToInt(Review::getScope)
                .average()
                .orElse(Double.NaN);
        return Math.round(averageScore *10.0)/10.0;
    }
}



