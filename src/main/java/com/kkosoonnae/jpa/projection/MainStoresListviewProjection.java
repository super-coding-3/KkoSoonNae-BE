package com.kkosoonnae.jpa.projection;

public record MainStoresListviewProjection(
        Integer storeNo,
        String storeName,
        String roadAddress,
        Double scope
) {
}
