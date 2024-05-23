package com.kkosoonnae.qna.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

/**
 * packageName    : com.kkosoonnae.qna.dto
 * fileName       : QnaListRsDto
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
@AllArgsConstructor
@NoArgsConstructor
public class QnaListDto {

    @Schema(description = "문의사항 일련번호")
    private Integer qnaNo;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "본문")
    private String content;

    @Schema(description = "생성일자")
    private LocalDateTime createDt;
}