package com.kkosoonnae.notice.service;

import com.kkosoonnae.jpa.entity.Notice;
import com.kkosoonnae.jpa.repository.NoticeRepository;
import com.kkosoonnae.notice.dto.NoticeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    public Optional<Notice> getNoticeById(Integer id) {
        return noticeRepository.findById(id);
    }
}
