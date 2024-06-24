package com.kkosoonnae.user.term.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.kkosoonnae.user.term.controller
 * fileName       : TermController
 * author         : hagjoon
 * date           : 2024-06-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-24        hagjoon       최초 생성
 */
@RestController
@RequestMapping("/api/user/term")
@Tag(name = "TermController",description = "약관 API 정보 컨트롤러")
@Slf4j
@RequiredArgsConstructor
public class TermController {


}
