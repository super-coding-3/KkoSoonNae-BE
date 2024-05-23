package com.kkosoonnae.reservation.dto;

import com.kkosoonnae.jpa.entity.Style;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StyleResponse {
    @Schema(description = "스타일 이름")
    private String cutStyle;

    public static StyleResponse styleToStyleResponse(Style style) {
        return StyleResponse.builder()
                .cutStyle(style.getStyleName())
                .build();
    }

    public static List<StyleResponse> stylesToStyleResponse(List<Style> styles) {
        return styles.stream().map(StyleResponse::styleToStyleResponse).collect(Collectors.toList());
    }
}
