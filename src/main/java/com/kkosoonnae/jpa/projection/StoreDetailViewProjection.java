package com.kkosoonnae.jpa.projection;

import lombok.Builder;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
public record StoreDetailViewProjection(
        Integer storeNo,
        String storeName,
        String content,
        String phone,
        String roadAddress,
        LocalTime openingTime,
        LocalTime closingTime,
        String img,
        Double averageScope,
        Long totalLikeStore
) {

}
