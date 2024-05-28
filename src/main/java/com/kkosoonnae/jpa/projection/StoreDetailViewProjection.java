package com.kkosoonnae.jpa.projection;

import com.kkosoonnae.store.dto.StoreDetailWithImageResponseDto;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Builder
public record StoreDetailViewProjection(
        Integer storeNo,
        String storeName,
        String content,
        String phone,
        String roadAddress,
        LocalTime openingTime,
        LocalTime closingTime,
        List<String> img,
        Double averageScope,
        Long totalLikeStore
) {
    public StoreDetailViewProjection(Integer storeNo, String storeName, String content, String phone,
                                     String roadAddress, LocalTime openingTime, LocalTime closingTime,
                                     Double averageScope, Long totalLikeStore) {
        this(storeNo, storeName, content, phone, roadAddress, openingTime, closingTime,
                new ArrayList<>(), averageScope, totalLikeStore);
    }

    }

