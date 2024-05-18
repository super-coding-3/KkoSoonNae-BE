package com.kkosoonnae.notice.controller;

import com.kkosoonnae.jpa.entity.Notice;
import com.kkosoonnae.notice.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * packageName    : com.kkosoonnae.notice.controller
 * fileName       : NoticeController
 * author         : hagjoon
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        hagjoon       최초 생성
 */
@RestController
@RequestMapping("/KkoSoonNae/notice")
@RequiredArgsConstructor
@Slf4j
public class NoticeController {

    private final NoticeService noticeService;

    @Operation(summary = "공지사항 전체 조회")
    @GetMapping("/all")
    public ResponseEntity<List<Notice>> getNoticeAll() {
        List<Notice> noticeList = noticeService.getNoticeAll();
        return ResponseEntity.ok().body(noticeList);
    }
}
