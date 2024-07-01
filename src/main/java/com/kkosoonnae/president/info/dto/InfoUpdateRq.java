package com.kkosoonnae.president.info.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.kkosoonnae.president.info.dto
 * fileName       : InfoUpdateRq
 * author         : hagjoon
 * date           : 2024-06-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-28        hagjoon       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InfoUpdateRq {

    @Schema(description = "담당자 명")
    private String name;

    @Schema(description = "이메일")
    private String email;

    @Schema(description = "전화번호")
    private String phone;
}
