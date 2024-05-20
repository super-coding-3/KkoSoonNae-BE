package com.kkosoonnae.point.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.kkosoonnae.point.dto
 * fileName       : PointDto
 * author         : hagjoon
 * date           : 2024-05-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-20        hagjoon       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PointDto {

    @Schema(description = "포인트 이름")
    private String title;

    @Schema(description = "잔여 포인트")
    private Double pointRm;
}
