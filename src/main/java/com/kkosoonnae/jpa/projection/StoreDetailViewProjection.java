package com.kkosoonnae.jpa.projection;

import lombok.Builder;

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
        Integer averageScope,
        Long totalLikeStore
) {
    public StoreDetailViewProjection(Integer storeNo, String storeName, String content, String phone,
                                     String roadAddress, LocalTime openingTime, LocalTime closingTime,
                                     Double averageScope, Long totalLikeStore) {
        this(storeNo, storeName, content, phone, roadAddress, openingTime, closingTime,
                new ArrayList<>(), averageScope, totalLikeStore);
    }

    }

