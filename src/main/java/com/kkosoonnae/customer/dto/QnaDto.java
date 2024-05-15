package com.kkosoonnae.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.kkosoonnae.customer.dto
 * fileName       : QnaDto
 * author         : hagjoon
 * date           : 2024-05-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-16        hagjoon       최초 생성
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QnaDto {

    @Schema(name = "제목")
    private String title;

    @Schema(name = "내용")
    private String content;

}
