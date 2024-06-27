package com.kkosoonnae.user.store.dto;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@Builder
public class StoreImgDto {
    private String img;

    public StoreImgDto(String img) {
        this.img = img;
    }
}


