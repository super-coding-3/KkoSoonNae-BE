package com.kkosoonnae.notice.service;

import com.kkosoonnae.jpa.repository.NoticeRepository;
import com.kkosoonnae.notice.dto.NoticeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.kkosoonnae.notice.service
 * fileName       : NoticeService
 * author         : hagjoon
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        hagjoon       최초 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;


    public NoticeDto findNoticeById(int id) {

        NoticeDto noticeDto = new NoticeDto();

        return noticeDto;
    }
}
