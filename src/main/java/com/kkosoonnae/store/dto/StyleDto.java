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
    private Integer styleId;
    private String styleName;
    private String img;
    private Integer price;


    public static StyleDto styleToDto(Style style) {
    return StyleDto.builder()
            .styleName(style.getStyleName())
            .img(style.getImg())
            .price(style.getPrice())
            .build();
    }

    public static List<StyleDto> styleToDto(List<Style> styles) {
        return styles.stream()
                .map(StyleDto::styleToDto)
                .collect(Collectors.toList());
    }

    public StyleDto(Style style){
        this.styleId=style.getStyleNo();
        this.styleName= style.getStyleName();
        this.img=style.getImg();
        this.price=style.getPrice();
    }
}
