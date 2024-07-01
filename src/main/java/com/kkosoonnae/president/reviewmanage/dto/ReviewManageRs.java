package com.kkosoonnae.president.reviewmanage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

/**
 * packageName    : com.kkosoonnae.president.review.dto
 * fileName       : ReviewManageRs
 * author         : hagjoon
 * date           : 2024-06-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-13        hagjoon       최초 생성
 */

@NoArgsConstructor
@Getter
@Setter
public class ReviewManageRs {
    @Schema(description = "리뷰 일련번호")
    private Integer reviewNo;

    @Schema(description = "매장 일련번호")
    private Integer storeNo;

    @Schema(description = "회원 일련번호")
    private Integer cstmrNo;

    @Schema(description = "사진")
    private String img;

    @Schema(description = "리뷰 내용")
    private String content;

    @Schema(description = "리뷰 등록 날짜")
    private DateTime reviewDt;

    @Schema(description = "별점")
    private Integer scope;

}
