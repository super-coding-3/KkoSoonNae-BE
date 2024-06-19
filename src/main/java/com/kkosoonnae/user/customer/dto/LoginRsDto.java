package com.kkosoonnae.user.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.kkosoonnae.user.customer.dto
 * fileName       : TokenResponseDto
 * author         : hagjoon
 * date           : 2024-06-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-17        hagjoon       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRsDto {

    private Integer custmrNo;

    private String loginId;

    private String nickName;

    @Schema(description = "토큰")
    private String token;
}
