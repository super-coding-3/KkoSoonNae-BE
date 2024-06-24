package com.kkosoonnae.user.term.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.kkosoonnae.user.term.dto
 * fileName       : TermRsDto
 * author         : hagjoon
 * date           : 2024-06-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-25        hagjoon       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TermRsDto {

    @Schema(description = "약관 제목")
    private String termName;

    @Schema(description = "약관 내용")
    private String termContent;

    @Schema(description = "필수")
    private String termFlag;
}
