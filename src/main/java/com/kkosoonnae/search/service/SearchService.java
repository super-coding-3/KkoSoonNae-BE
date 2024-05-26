package com.kkosoonnae.search.service;

import com.kkosoonnae.jpa.projection.MainStoresListviewProjection;
import com.kkosoonnae.jpa.projection.StoreListViewProjection;
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
    public List<StoreListViewResponseDto> findByStores(String nameAddressKeyword) {
        if (nameAddressKeyword == null || nameAddressKeyword.isEmpty()) {
            throw new CustomException(ErrorCode.STORE_NOT_FOUND);
        }
            List<StoreListViewProjection> projection = storeRepository.findStoresByStoreNameInAndAddressInOrderByAddressAsc(nameAddressKeyword);
            if (projection.isEmpty()) {
                throw new CustomException(ErrorCode.STORE_NOT_FOUND);
            }
            return projection.stream()
                    .map(StoreListViewResponseDto::projectToDto)
                    .collect(Collectors.toList());
        }

    public List<MainStoreListViewResponseDto> findByMainStores(Pageable pageable) {
        List<MainStoresListviewProjection> projections = storeRepository.findMainStores("강남구" , pageable);
        if (projections.isEmpty()) {
            throw new CustomException(ErrorCode.STORE_NOT_FOUND);
        }
        Collections.shuffle(projections);
        return projections.stream()
                .map(MainStoreListViewResponseDto::mainStoresDto)
                .collect(Collectors.toList());

     }
}


