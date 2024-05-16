package com.kkosoonnae.map.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NearByStore {
    @Schema(description = "매장 이름")
    private String placeName;

    @Schema(description = "매장설명")
    private String content;

    @Schema(description = "별점")
    private String scope;

    @Schema(description = "매장이미지")
    private String img;


}
