package com.kkosoonnae.user.search.service;

import com.kkosoonnae.common.exception.CustomException;
import com.kkosoonnae.common.exception.ErrorCode;
import com.kkosoonnae.jpa.entity.Review;
import com.kkosoonnae.jpa.entity.Store;
import com.kkosoonnae.jpa.projection.MainStoresListviewProjection;
import com.kkosoonnae.jpa.repository.ReviewRepository;
import com.kkosoonnae.jpa.repository.StoreRepository;
import com.kkosoonnae.user.search.dto.MainStoreListViewResponseDto;
import com.kkosoonnae.user.search.dto.StoreListViewResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;



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

    public List<StoreListViewResponseDto> findByStores(String nameAddressKeyword) {
        List<Store> stores;
        try {
            stores = storeRepository.findListStores(nameAddressKeyword);
            if (stores.isEmpty()) {
                throw new CustomException(ErrorCode.STORE_NOT_FOUND);
            }
            return stores.stream()
                    .map(store -> {
                        double averageScope = getAverageReviewScore(store.getStoreNo());
                        return new StoreListViewResponseDto(store, averageScope);
                    })
                    .collect(Collectors.toList());
        } catch (DataAccessException dae) {
            throw new CustomException(ErrorCode.DATABASE_ERROR);
        }
    }

    public List<MainStoreListViewResponseDto> findByMainStores(String addressKeyword, Pageable pageable) {
        try {
            List<MainStoresListviewProjection> projections = storeRepository.findMainStores(addressKeyword, pageable);
            if (projections.isEmpty()) {
                throw new CustomException(ErrorCode.STORE_NOT_FOUND);
            }
            Collections.shuffle(projections);
            return projections.stream()
                    .map(projection -> {
                        double averageScope = getAverageReviewScore(projection.storeNo());
                        return projection.MainStoreToDto(averageScope);
                    })
                    .collect(Collectors.toList());
        } catch (DataAccessException dae) {
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



