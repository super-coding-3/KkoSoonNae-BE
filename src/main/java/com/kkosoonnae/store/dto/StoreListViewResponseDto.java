package com.kkosoonnae.store.dto;

import com.kkosoonnae.jpa.projection.StoreListViewProjection;
import lombok.Builder;

import java.util.Collections;
import java.util.List;

@Builder
public record StoreListViewResponseDto (
        List<StoreListViewProjection> stores
) {

    public StoreListViewResponseDto {
        if (stores == null) {
            stores = Collections.emptyList();
        }
    }
}
