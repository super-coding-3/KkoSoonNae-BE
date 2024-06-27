package com.kkosoonnae.president.info.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.kkosoonnae.user.customer.dto
 * fileName       : TermDto
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
@AllArgsConstructor
@NoArgsConstructor
public class TermRq {

    @Schema(description = "약관 일련번호")
    private Integer termNo;

    @Schema(description = "동의 여부",example = "Y")
    private String agreeYn;
}
