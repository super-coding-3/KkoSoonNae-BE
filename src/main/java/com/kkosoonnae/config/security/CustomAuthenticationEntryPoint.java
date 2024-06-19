package com.kkosoonnae.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * packageName    : com.kurly.api.config.security
 * fileName       : CustomAuthenticationEntryPoint
 * author         : hagjoon
 * date           : 2024-04-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-24        hagjoon       최초 생성
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        // HTTP 요청이 JSON 형식인 경우만 처리
        String contentType = request.getHeader("Content-Type");

        if(contentType != null && contentType.contains("application/json")){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 인증 실패 상태 코드
            response.setContentType("application/json"); // JSON 형식으로 응답
            response.setCharacterEncoding("UTF-8"); // 문자 인코딩 설정

            // 오류 메시지 JSON 형식으로 작성
            PrintWriter writer = response.getWriter();
            writer.println("{ \"error\": \"이메일 또는 비밀번호가 올바르지 않습니다.\"}");
            writer.flush();
        }else {
            // JSON 형식이 아닌 경우에는 리다이렉트하여 로그인 페이지로 이동
            response.sendRedirect("/api/user/customer/login");
        }
        if (request.getRequestURI().equals("/api/user/customer/login")){
            // 이미 로그인 페이지로 요청 중인 경우, 다시 리다이렉트할 필요가 없음
            return;
        }
        response.sendRedirect("/api/user/customer/login");

    }
}
