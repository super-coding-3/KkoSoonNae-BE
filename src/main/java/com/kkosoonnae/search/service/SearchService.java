package com.kkosoonnae.search.service;

import com.kkosoonnae.jpa.entity.Review;
import com.kkosoonnae.jpa.entity.StoreImg;
import com.kkosoonnae.jpa.projection.MainStoresListviewProjection;
import com.kkosoonnae.jpa.projection.StoreListViewProjection;
import com.kkosoonnae.jpa.repository.ReviewRepository;
import com.kkosoonnae.jpa.repository.StoreImgRepository;
import com.kkosoonnae.jpa.repository.StoreRepository;
import com.kkosoonnae.search.dto.MainStoreListViewResponseDto;
import com.kkosoonnae.search.dto.StoreListViewResponseDto;
import com.kkosoonnae.store.exception.CustomException;
import com.kkosoonnae.store.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
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
    private final StoreImgRepository storeImgRepository;
    public List<StoreListViewResponseDto> findByStores(String nameAddressKeyword) {
        if (nameAddressKeyword == null || nameAddressKeyword.isEmpty()) {
            throw new CustomException(ErrorCode.STORE_NOT_FOUND);
        }
        List<StoreListViewProjection> projection = storeRepository.findStoresByStoreNameInAndAddressInOrderByAddressAsc(nameAddressKeyword);
        if (projection.isEmpty()) {
            throw new CustomException(ErrorCode.STORE_NOT_FOUND);
        }
        List<Integer> storeNo = projection.stream()
                .map(StoreListViewProjection::storeNo)
                .collect(Collectors.toList());

        List<String> storeImgList = storeImgRepository.findStoresImgStoreNo(storeNo);

        Double averageScope = calculateAverageScope();

        return projection.stream()
                .map(store ->store.toDto(storeImgList,averageScope))
                .collect(Collectors.toList());

    }
    public List<MainStoreListViewResponseDto> findByMainStores(String addressKeyword ,Pageable pageable) {
        List<MainStoresListviewProjection> projections = storeRepository.findMainStores(addressKeyword, pageable);
        if (projections.isEmpty()) {
            throw new CustomException(ErrorCode.STORE_NOT_FOUND);
        }
        Double averageScope = calculateAverageScope();
        Collections.shuffle(projections);
        return projections.stream()
                .map(listviewProjection-> listviewProjection.MainStoreToDto(averageScope))
                .collect(Collectors.toList());

     }
    private Double calculateAverageScope() {
        List<Review> reviews = reviewRepository.findAll();
        if (reviews.isEmpty()) {
            return 0.0;
        }
        int totalScope = reviews.stream().mapToInt(Review::getScope).sum();
        double average = (double) totalScope / reviews.size();
        return Math.round(average * 10) /10.0;
    }
}


