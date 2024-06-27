package com.kkosoonnae.user.store.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreImgDto {
    private Integer storeImgNo;

    private Integer storeNo;

    private List<String> img;


    }


