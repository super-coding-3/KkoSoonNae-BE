package com.kkosoonnae.notice.controller;

import com.kkosoonnae.jpa.entity.Notice;
import com.kkosoonnae.notice.dto.NoticeDto;
import com.kkosoonnae.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{id}")
    public Notice notice(@PathVariable("id") int id) {
        NoticeDto noticeDto = noticeService.findNoticeById(id);
    }
}
