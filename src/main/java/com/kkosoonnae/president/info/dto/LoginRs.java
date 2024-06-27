package com.kkosoonnae.president.info.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.kkosoonnae.president.info.dto
 * fileName       : LoginRs
 * author         : hagjoon
 * date           : 2024-06-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-27        hagjoon       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRs {

    @Schema(description = "회원 일련번호")
    private Integer cstmrNo;

    @Schema(description = "로그인 아이디")
    private String loginId;

    @Schema(description = "닉네임")
    private String nickName;

    @Schema(description = "토큰")
    private String token;

}
