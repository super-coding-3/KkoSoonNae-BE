package com.kkosoonnae.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.kkosoonnae.customer.dto
 * fileName       : InfoDto
 * author         : hagjoon
 * date           : 2024-05-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-12        hagjoon       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InfoDto {

    @Schema(description = "닉네임")
    private String nickName;

    @Schema(description = "핸드폰 번호")
    private String phone;

    @Schema(description = "우편번호")
    private String zipCode;

    @Schema(description = "기본주소")
    private String address;

    @Schema(description = "상세주소")
    private String addressDtl;

}
