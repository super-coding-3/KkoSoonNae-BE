package com.kkosoonnae.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * packageName    : com.kkosoonnae.member.dto
 * fileName       : SignUpDto
 * author         : hagjoon
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        hagjoon       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {

    @Schema(description = "로그인 Id")
    private String loginId;

    @Schema(description = "이메일")
    private String email;

    @Schema(description = "비밀번호")
    private String password;

    @Schema(description = "핸드폰번호")
    private String phone;

    @Schema(description = "닉네임")
    private String nickName;

    @Schema(description = "우편번호")
    private String zipCode;

    @Schema(description = "기본주소")
    private String address;

    @Schema(description = "상세주소")
    private String addressDtl;
}
