package com.kkosoonnae.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * packageName    : com.kurly.api.config.security
 * fileName       : CustomerAccessDeniedHandler
 * author         : hagjoon
 * date           : 2024-04-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-24        hagjoon       최초 생성
 */
@Slf4j
@Component
public class CustomerAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 사용자가 접근 권한이 없는 자원에 접근했을 때 실행 되는 메소드
        // 접근 거부 예외의 스택 트레이스를 콘솔에 출력
        accessDeniedException.printStackTrace();
        response.sendRedirect("/api/user/customer/login");

    }
}
