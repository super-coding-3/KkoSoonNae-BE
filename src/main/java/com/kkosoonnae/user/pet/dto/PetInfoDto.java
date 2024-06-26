package com.kkosoonnae.user.pet.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * packageName    : com.kkosoonnae.customer.dto
 * fileName       : PetInfoDto
 * author         : hagjoon
 * date           : 2024-05-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-14        hagjoon       최초 생성
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PetInfoDto {

    @Schema(description = "반려동물 일련번호")
    private Integer petNo;

    @Schema(description = "이미지")
    private String img;

    @Schema(description = "이름")
    private String name;

    @Schema(description = "종류")
    private String type;

    @Schema(description = "생년월일")
    private LocalDate birthDt;

    @Schema(description = "성별")
    private String gender;

    @Schema(description = "몸무게")
    private String weight;

    @Schema(description = "대표 반려동물")
    private String mainPet;

}
