package com.kkosoonnae.pet.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.kkosoonnae.pet.dto
 * fileName       : PetListRqDto
 * author         : hagjoon
 * date           : 2024-05-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-16        hagjoon       최초 생성
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PetListRqDto {

    @Schema(description = "이미지")
    private String img;

    @Schema(description = "이름")
    private String name;

    @Schema(description = "종류")
    private String type;

    @Schema(description = "생년월일")
    private String birthDt;
}
