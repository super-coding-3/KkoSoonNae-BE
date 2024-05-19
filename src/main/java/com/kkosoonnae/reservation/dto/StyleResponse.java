package com.kkosoonnae.reservation.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kkosoonnae.jpa.entity.Style;
import com.kkosoonnae.store.dto.StyleDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StyleResponse {
    @Schema(description = "스타일 이름")
    private String styleName;

//    public StyleResponse(Style style) {
//        this.styleName = style.getStyleName();
//    }

    public static StyleResponse styleToStyleResponse(Style style) {
        return StyleResponse.builder()
                .styleName(style.getStyleName())
                .build();
    }

    public static List<StyleResponse> stylesToStyleResponse(List<Style> styles) {
        return styles.stream().map(StyleResponse::styleToStyleResponse).collect(Collectors.toList());
    }
}
