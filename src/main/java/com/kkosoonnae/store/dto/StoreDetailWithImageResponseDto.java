package com.kkosoonnae.store.dto;

import com.kkosoonnae.jpa.entity.LikeStore;
import com.kkosoonnae.jpa.entity.StoreImg;
import com.kkosoonnae.jpa.projection.StoreDetailViewProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDetailWithImageResponseDto {
    private StoreDetailViewProjection storeDetail;

}





