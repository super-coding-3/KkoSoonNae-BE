package com.kkosoonnae.store.dto;

import com.kkosoonnae.jpa.entity.Style;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StyleDto {
    private String styleName;
    private String img;
    private Integer price;


    public static StyleDto styleToEntity(Style style) {
    return StyleDto.builder()
            .styleName(style.getStyleName())
            .img(style.getImg())
            .price(style.getPrice())
            .build();
    }

    public static List<StyleDto> styleToEntity(List<Style> styles) {
        return styles.stream()
                .map(StyleDto::styleToEntity)
                .collect(Collectors.toList());
    }
}
