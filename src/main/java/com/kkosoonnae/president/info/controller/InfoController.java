package com.kkosoonnae.president.info.controller;

import com.kkosoonnae.common.exception.CustomException;
import com.kkosoonnae.common.exception.ErrorCode;
import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.config.jwt.JwtProperties;
import com.kkosoonnae.president.info.dto.*;
import com.kkosoonnae.president.info.service.InfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/login")
    @Operation(summary = "로그인")
    public ResponseEntity<Map<String, Object>> logIn(@RequestBody LoginRq login, HttpServletResponse httpServletResponse) {
        try {
            // 로그인 시도 및 jwt 토큰 생성
            LoginRs tokenResponse = infoService.login(login);

            Map<String, Object> data = new HashMap<>();
            data.put("token", tokenResponse.getToken());

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("data", tokenResponse);
            responseBody.put("message", "로그인에 성공하였습니다. 토큰을 발급합니다.");

            httpServletResponse.setHeader(JwtProperties.HEADER_STRING, tokenResponse.getToken());
            log.info("jwt 토큰 : {}", tokenResponse.getToken());
            log.info("로그인 완료");
            return ResponseEntity.ok(responseBody);

        } catch (BadCredentialsException e) {
            Map<String, Object> errorBody = new HashMap<>();
            errorBody.put("error", "이메일 또는 비밀번호가 올바르지 않습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorBody);
        } catch (Exception e) {
            Map<String, Object> errorBody = new HashMap<>();
            errorBody.put("error", "로그인 처리 중 문제가 발생하였습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorBody);
        }
    }

    @PutMapping("/update")
    @Operation(summary = "사장 기본 정보 수정")
    public ResponseEntity<InfoUpdateRs> update(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody InfoUpdateRq rq){
        Integer cstmrNo = principalDetails.getCustomerBas().getCstmrNo();
        InfoUpdateRs rs = infoService.updateInfo(cstmrNo,rq);
        return ResponseEntity.ok(rs);
    }
}
