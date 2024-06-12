package com.kkosoonnae.user.notice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * packageName    : com.kkosoonnae.notice.dto
 * fileName       : NoticeDto
 * author         : hagjoon
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        hagjoon       최초 생성
 */
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoticeDto {

    @Schema(description = "공지사항 번호" , example = "1")
    private Integer noticeNo;

    @Schema(description = "제목", example = "꼬순내샵 업데이트 공지")
    private String Title;

    @Schema(description = "내용", example = "내 꼬순내 스타일 예약 시 스타일 선택 가능하게 변경됩니다")
    private String Content;

    @Schema(description = "공지사항 등록날짜" , example = "2024.05.11")
    private String Date;

    @Schema(description = "조회 수", example = "531")
    private String viewCount;

    @Schema(description = "이미지", example = "null")
    private String img;
}
