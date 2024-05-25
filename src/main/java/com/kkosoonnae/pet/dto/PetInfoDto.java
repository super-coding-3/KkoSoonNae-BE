package com.kkosoonnae.pet.dto;

import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.jpa.entity.Pet;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

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
    private String birthDt;

    @Schema(description = "성별")
    private String gender;

    @Schema(description = "몸무게")
    private String weight;

}
