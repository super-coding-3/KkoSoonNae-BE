package com.kkosoonnae.user.store.dto;

import com.kkosoonnae.jpa.projection.StoreDetailViewProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDetailWithImageResponseDto {

    private StoreDetailViewProjection storeDetailViewProjection;

}





