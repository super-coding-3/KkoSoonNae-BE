package com.kkosoonnae.pet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String img;

    private String name;

    private String type;

    private String birthDt;

    private String gender;

    private String weight;
}
