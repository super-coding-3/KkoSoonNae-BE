package com.kkosoonnae.president.info.dto;

import com.kkosoonnae.user.customer.dto.TermDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.kkosoonnae.president.info.dto
 * fileName       : SignUpDto
 * author         : hagjoon
 * date           : 2024-06-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-20        hagjoon       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {

    @Schema(description = "로그인 ID")
    private String loginId;

    @Schema(description = "비밀번호")
    private String password;

    @Schema(description = "이메일")
    private String email;

    @Schema(description = "핸드폰 번호")
    private String phone;

    @Schema(description = "닉네임")
    private String nickName;

    private TermDto[] terms;
}
