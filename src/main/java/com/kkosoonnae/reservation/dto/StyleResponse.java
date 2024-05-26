package com.kkosoonnae.reservation.dto;

import com.kkosoonnae.jpa.entity.Style;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StyleResponse {
    @Schema(description = "스타일 이름")
    private String cutStyle;

    @Schema(description = "스타일 가격")
    private String price;

    public static StyleResponse styleToStyleResponse(Style style) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.KOREA);
        String formattedPrice = numberFormat.format(style.getPrice());
        String stringPrice = formattedPrice + "원";

        return StyleResponse.builder()
                .cutStyle(style.getStyleName())
                .price(stringPrice)
                .build();
    }

    public static List<StyleResponse> stylesToStyleResponse(List<Style> styles) {
        return styles.stream().map(StyleResponse::styleToStyleResponse).collect(Collectors.toList());
    }
}
