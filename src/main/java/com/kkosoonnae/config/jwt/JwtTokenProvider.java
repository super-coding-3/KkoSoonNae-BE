//package com.kkosoonnae.config.jwt;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import io.jsonwebtoken.security.SignatureException;
//import jakarta.annotation.PostConstruct;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//
///**
// * packageName    : com.kurly.api.config.jwt
// * fileName       : JwtTokenProvider
// * author         : hagjoon
// * date           : 2024-04-23
// * description    :
// * ===========================================================
// * DATE              AUTHOR             NOTE
// * -----------------------------------------------------------
// * 2024-04-23        hagjoon       최초 생성
// */
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class JwtTokenProvider {
//
//    @Value("${security.jwt.secret-key}")
//    private String secretKeySource;
//
//    private Key key;
//
//    private final UserDetailsService userDetailsService; //사용자 정보 로드 서비스
//
//    @PostConstruct
//    public void setUp(){
//      byte[] secretKey = Decoders.BASE64.decode(secretKeySource);
//      key = Keys.hmacShaKeyFor(secretKey);
//    }
//
//    //HTTP 요청에서 토큰 가져옴
//    public String resolveToken(HttpServletRequest request){
//        String bearerToken = request.getHeader(JwtProperties.HEADER_STRING);
//
//        if(bearerToken != null && bearerToken.startsWith(JwtProperties.TOKEN_PREFIX)){
//            return bearerToken.substring(7); // "Bearer " 무시
//        }
//        return null;
//    }
//    public String createToken(String login){
//        Date now = new Date();
//        return Jwts.builder()
//                .setSubject(login) //토큰 주제 이메일로 설정
//                .setIssuedAt(now) // 토크 발행 시간
//                .setExpiration(new Date(now.getTime() + JwtProperties.EXPIRATION_TIME)) // 토큰 만료시간
//                .signWith(key,SignatureAlgorithm.HS256) // HS256 알고리즘과 비밀키를 사용하여 서명
//                .compact();
//    }
//
//    //JWT 토큰 유효성 검증
//    public boolean validateToken(String jwtToken){
//        try{
//            // JWT 토큰 파싱 후 CLaim 객체를 얻음
//            Claims claims = Jwts.parserBuilder()
//                    .setSigningKey(key)
//                    .build()
//                    .parseClaimsJws(jwtToken)
//                    .getBody();
//
//            Date expirationDate = claims.getExpiration();
//            Date now = new Date();
//            // 토크의 만료 시간이 현재 시간보다 이전이거나 동일하면 만료
//            if(expirationDate != null && expirationDate.before(now)){
//                log.error("JWT token 시간 만료");
//                return false;
//            }
//            //서명이 올바른 경우
//            return true;
//        }catch (ExpiredJwtException e){
//            log.error(e.getMessage());
//            return false;
//        }catch (SignatureException e){
//            log.error(e.getMessage());
//            return false;
//        }catch (Exception e){
//            log.error(e.getMessage());
//            return false;
//        }
//    }
//
//    // 주어진 JWT 토큰에서 사용자의 이메일을 추출하여 UserDetails를 로드, 인증 객체 반환
//    public Authentication getAuthentication(String jwtToken){
//        // 주어진 JWT 토큰에서 사용자 이메일 추출
//        String login = getMemberLoginId(jwtToken);
//        UserDetails userDetails = userDetailsService.loadUserByUsername(login);
//        return new UsernamePasswordAuthenticationToken(userDetails, "",userDetails.getAuthorities());
//
//        //UserDetails를 사용하여 인증 객체 생성
//        //인증에는 UserDetails와 빈 문자열(비밀번호 필드가 없는 경우) 그리고 해당 사용자의 권한이 포함.
//    }
//
//    private String getMemberLoginId(String jwtToken) {
//        String memberLoginId = Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(jwtToken)
//                .getBody().getSubject();
//        return memberLoginId;
//    }
//}
