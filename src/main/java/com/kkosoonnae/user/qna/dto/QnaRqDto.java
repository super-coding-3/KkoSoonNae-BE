package com.kkosoonnae.user.qna.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * packageName    : com.kkosoonnae.qna.dto
 * fileName       : QnaRqDto
 * author         : hagjoon
 * date           : 2024-05-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-17        hagjoon       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QnaRqDto {

    @Schema(description = "제목")
    private String title;

    @Schema(description = "본문내용")
    private String content;

    @Schema(description = "답변상태",defaultValue = "N")
    private String qnaState;

    @Schema(description = "생성일자")
    private LocalDateTime createDt;
}
