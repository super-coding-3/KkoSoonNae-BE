package com.kkosoonnae.store.dto;

import com.kkosoonnae.jpa.entity.StoreImg;
import com.kkosoonnae.jpa.projection.StoreDetailViewProjection;
import lombok.Builder;

import java.util.Collections;
import java.util.List;

@Builder
public record StoreDetailViewResponseDto (
    List<StoreDetailViewProjection> store,

    StoreImg img

) {
    public StoreDetailViewResponseDto {
        if (store == null) {
            store = Collections.emptyList();

        }
    }
}

