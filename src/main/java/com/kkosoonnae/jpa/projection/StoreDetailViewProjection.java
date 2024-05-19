package com.kkosoonnae.jpa.projection;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record StoreDetailViewProjection(
        Integer storeNo,
        String storeName,
        String content,
        String zipCode,
        String address,
        String addressDtl,
        String phone,
        LocalDateTime storeOperDt,
        String roadAddress
) {
}
