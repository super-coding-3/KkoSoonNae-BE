package com.kkosoonnae.jpa.projection;

import lombok.Builder;

@Builder
public record MainStoresListviewProjection(
        Integer storeNo,
        String storeName,
        String roadAddress,
        Double scope
) {
}
