package com.kkosoonnae.president.mystore.dto;

import com.kkosoonnae.jpa.entity.Style;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AdminPetStyleResponseDto {
    private Integer styleNo;
    private Integer storeNo;
    private String img;
    private String styleName;
    private Integer price;


    public AdminPetStyleResponseDto petStyleToDto(Style style) {
        return AdminPetStyleResponseDto.builder()
                .styleNo(style.getStyleNo())
                .storeNo(style.getStore().getStoreNo())
                .img(style.getImg())
                .styleName(style.getStyleName())
                .price(style.getPrice())
                .build();

    }
    public AdminPetStyleResponseDto(Style style) {
        this.styleNo = style.getStyleNo();
        this.storeNo = style.getStore().getStoreNo();
        this.img = style.getImg();
        this.styleName = style.getStyleName();
        this.price = style.getPrice();

    }
}

