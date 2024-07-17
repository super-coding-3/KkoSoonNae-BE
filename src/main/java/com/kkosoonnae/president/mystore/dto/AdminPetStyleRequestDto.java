package com.kkosoonnae.president.mystore.dto;

import com.kkosoonnae.jpa.entity.Store;
import com.kkosoonnae.jpa.entity.Style;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AdminPetStyleRequestDto {
    private String img;
    private String styleName;
    private Integer price;


    public Style styleToEntity(Store store,String imgUrl) {
        return Style.builder()
                .store(store)
                .img(imgUrl)
                .styleName(this.styleName)
                .price(this.price)
                .build();

    }
}
