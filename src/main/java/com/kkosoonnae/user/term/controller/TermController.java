package com.kkosoonnae.user.term.controller;

import com.kkosoonnae.user.term.dto.TermRsDto;
import com.kkosoonnae.user.term.service.TermService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    private final TermService service;

    @GetMapping()
    @Operation(summary = "약관 동의 목록")
    public ResponseEntity<List<TermRsDto>> list(){
        List<TermRsDto> list = service.getTerm();
        return ResponseEntity.ok().body(list);
    }
}
