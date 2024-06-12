package com.kkosoonnae.user.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * packageName    : com.kkosoonnae.customer.dto
 * fileName       : LoginDto
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
public class LoginDto {

    @Schema(description = "로그인 ID")
    private String loginId;

    @Schema(description = "비밀버호")
    private String password;
}
