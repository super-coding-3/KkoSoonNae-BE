package com.kkosoonnae.president.info.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.kkosoonnae.president.info.controller
 * fileName       : InfoController
 * author         : hagjoon
 * date           : 2024-06-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-14        hagjoon       최초 생성
 */
@RestController
@RequestMapping("/api/president/info")
@Tag(name = "InfoController",description = "사장 회원 정보 API 컨트롤러")
@Slf4j
@RequiredArgsConstructor
public class InfoController {
}
