package com.kkosoonnae.president.mystore.dto;

import com.kkosoonnae.jpa.entity.Store;
import com.kkosoonnae.jpa.entity.StoreImg;
import lombok.*;

import java.util.Optional;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdminStoreImgRequestDto {
    private Integer storeNo;
    private String imgUrl;
    
    public void updateImgEntity(StoreImg storeImg) {
        storeImg.setImg(this.imgUrl);

    }
}

