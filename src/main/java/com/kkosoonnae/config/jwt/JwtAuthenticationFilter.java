//package com.kkosoonnae.config.jwt;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
///**
// * packageName    : com.kurly.api.config.jwt
// * fileName       : JwtAutenticationFilter
// * author         : hagjoon
// * date           : 2024-04-23
// * description    :
// * ===========================================================
// * DATE              AUTHOR             NOTE
// * -----------------------------------------------------------
// * 2024-04-23        hagjoon       최초 생성
// */
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JwtTokenProvider jwtTokenProvider;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        // 요청에서 JWT 토큰 추출
//        String jwtToken = jwtTokenProvider.resolveToken(request);
//        // 추출된 토큰 유효한지 확인
//        if(jwtToken != null && jwtTokenProvider.validateToken(jwtToken)){
//            // 유효한 토큰으로부터 인증 정보를 가져옴
//            Authentication auth = jwtTokenProvider.getAuthentication(jwtToken);
//            // 인증 정보를 SecurityContextHolder에 설정하여 현재의 SecurityContext에 인증된 사용자 정보를 설정
//            SecurityContextHolder.getContext().setAuthentication(auth);
//        }
//        // 다음 필터로  요청을 전달
//        filterChain.doFilter(request,response);
//    }
//}
