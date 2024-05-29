package com.kkosoonnae.pet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.jpa.entity.Pet;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

/**
 * packageName    : com.kkosoonnae.pet.dto
 * fileName       : PetAddDto
 * author         : hagjoon
 * date           : 2024-05-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-26        hagjoon       최초 생성
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetAddDto {

    @Schema(description = "반려동물 일련번호")
    @JsonIgnore
    private Integer petNo;

    @JsonIgnore
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


    public Pet addPet(PrincipalDetails principalDetails,boolean hasPets){
        Pet pet = Pet.builder()
                .petNo(petNo)
                .customerBas(principalDetails.getCustomerBas())
                .img(img)
                .name(name)
                .type(type)
                .birthDt(birthDt)
                .gender(gender)
                .weight(weight)
                .mainPet(hasPets ? "N" : "Y")
                .build();
        return pet;
    }
}
