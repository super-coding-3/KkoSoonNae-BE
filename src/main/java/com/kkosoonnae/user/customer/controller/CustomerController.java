package com.kkosoonnae.user.customer.controller;

import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.config.jwt.JwtProperties;
import com.kkosoonnae.config.security.CustomLogoutHandler;
import com.kkosoonnae.user.customer.dto.InfoDto;
import com.kkosoonnae.user.customer.dto.LoginDto;
import com.kkosoonnae.user.customer.dto.LoginRsDto;
import com.kkosoonnae.user.customer.dto.SignUpDto;
import com.kkosoonnae.user.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : com.kkosoonnae.member.controller
 * fileName       : MemberController
 * author         : hagjoon
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        hagjoon       최초 생성
 */
@RestController
@RequestMapping("/api/user/customer")
@Tag(name = "CustomerController",description = "회원 API 정보 컨트롤러")
@Slf4j
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    private final CustomLogoutHandler customLogoutHandler;

    @PostMapping("/signUp")
    @Operation(summary = "회원 가입")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {
        try {
            boolean isSuccess = service.signUp(signUpDto);
            if (isSuccess) {
                return ResponseEntity.ok(Collections.singletonMap("message", "회원가입이 완료되었습니다."));
            } else {
                return ResponseEntity.badRequest().body(Collections.singletonMap("message", "회원가입에 실패했습니다."));
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", e.getMessage()));
        }
    }

    @GetMapping("/check/login/{loginId}")
    @Operation(summary = "로그인 아이디 중복체크")
    public ResponseEntity<?> validationLoginId(@PathVariable String loginId) {
        if (service.existsLoginId(loginId)) {
            return ResponseEntity.ok(Collections.singletonMap("message", "중복된 아이디 입니다."));
        } else {
            return ResponseEntity.ok(Collections.singletonMap("message", "사용 가능한 아이디 입니다."));
        }
    }

    @GetMapping("/check/nickname/{nickName}")
    @Operation(summary = "닉네임 중복체크")
    public ResponseEntity<?> validationNickName(@PathVariable String nickName) {
        if (service.existsNickName(nickName)) {
            return ResponseEntity.ok(Collections.singletonMap("message", "중복된 닉네임 입니다."));
        } else {
            return ResponseEntity.ok(Collections.singletonMap("message", "사용 가능한 닉네임 입니다."));
        }
    }



    @PostMapping("/login")
    @Operation(summary = "로그인")
    public ResponseEntity<Map<String, Object>> logIn(@RequestBody LoginDto login, HttpServletResponse httpServletResponse) {
        try {
            // 로그인 시도 및 jwt 토큰 생성
            LoginRsDto tokenResponse = service.login(login);

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




    @Operation(summary = "로그아웃")
    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        customLogoutHandler.logout(request,response,null);
        log.info("로그아웃 완료");
    }

    @Operation(summary = "회원 정보 조회")
    @GetMapping("/profile")
        public ResponseEntity<InfoDto> getUserProFile(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        try {
            Integer cstmrNo = principalDetails.getCustomerBas().getCstmrNo();
            InfoDto userProfile = service.getUserProfile(cstmrNo);
            log.info("userProFile : {} ", userProfile);
            return ResponseEntity.ok(userProfile);
        } catch (UsernameNotFoundException e) {
            // 사용자를 찾을 수 없는 경우 예외 처리
            log.error("사용자를 찾을 수 없습니다: ", e);
            InfoDto errorResponse = new InfoDto();
            errorResponse.setError("사용자를 찾을 수 없습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            // 그 외의 예외 처리
            log.error("프로필을 가져오는 중 문제가 발생했습니다: ", e);
            InfoDto errorResponse = new InfoDto();
            errorResponse.setError("프로필을 가져오는 중 문제가 발생했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Operation(summary = "회원 정보 수정")
    @PutMapping("/profile/update")
    public ResponseEntity<?> updateUserProfile(@RequestBody InfoDto infoDto,
                                               @AuthenticationPrincipal PrincipalDetails principalDetails){
        try{
            Integer cstmrNo = principalDetails.getCustomerBas().getCstmrNo();
            Map<String,String> response = new HashMap<>();
            service.updateUserProfile(infoDto,cstmrNo);
            response.put("message","회원 정보 수정에 성공 하였습니다.");
            return ResponseEntity.ok(response);
        }catch (IllegalStateException e){
            log.error("사용자 정보를 찾을 수 없습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자의 정보를 찾을 수 없습니다.");
        }
    }

    @Operation(summary = "로그인한 회원 닉네임")
    @GetMapping("/nickname")
    public ResponseEntity<?> getUserNickname(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        try {
            Integer cstmrNo = principalDetails.getCustomerBas().getCstmrNo();
            String nickname = service.getUserNickname(cstmrNo);
            return ResponseEntity.ok(nickname);
        } catch (IllegalStateException e) {
            // 사용자의 상세 정보를 찾을 수 없는 경우
            log.error("사용자 정보 찾을 수 없는 경우 : {} ",e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자의 상세 정보를 찾을 수 없습니다.");
        } catch (Exception e) {
            // 그 외 예외 처리
            log.error("그 외 예외 발생 했을 경우 : {}",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
        }
    }

}
