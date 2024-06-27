package com.kkosoonnae.jpa.projection;

import com.kkosoonnae.user.search.dto.MainStoreListViewResponseDto;
import lombok.Builder;

@Builder
public record MainStoresListviewProjection(
        Integer storeNo,
        String storeName,
        String roadAddress,
        Long totalLikeStore


) {

    }


