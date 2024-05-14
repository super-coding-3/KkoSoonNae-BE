package com.kkosoonnae.jpa.projection;

import lombok.Builder;

@Builder
public record StoreListViewProjection(
        Integer storeNo,
        String storeName
        // ... (목록 조회 때 보여 줄 필드만 작성)
) {
}
