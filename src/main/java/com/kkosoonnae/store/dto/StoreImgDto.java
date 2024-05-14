package com.kkosoonnae.store.dto;

import com.kkosoonnae.jpa.entity.StoreImg;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreImgDto {
    private Integer storeImgNo;
    private Integer storeNo;
    private String img;

    }

