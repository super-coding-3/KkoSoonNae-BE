package com.kkosoonnae.president.info.controller;

import com.kkosoonnae.president.info.dto.SignUpDto;
import com.kkosoonnae.president.info.service.InfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

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

    private final InfoService infoService;

    @PostMapping("/signup")
    @Operation(summary = "사장 회원 가입")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto){
        try {
            boolean isSuccess = infoService.signUp(signUpDto);
            if(isSuccess){
                return ResponseEntity.ok(Collections.singletonMap("message","회원가입에 성공하였습니다."));
            }else {
                return ResponseEntity.badRequest().body(Collections.singletonMap("message","회원가입에 실패했습니다."));
            }
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", e.getMessage()));
        }
    }
}
