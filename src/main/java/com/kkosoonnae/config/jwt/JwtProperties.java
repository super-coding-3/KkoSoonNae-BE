package com.kkosoonnae.config.jwt;

/**
 * packageName    : com.kurly.api.config.jwt
 * fileName       : JwtProperties
 * author         : hagjoon
 * date           : 2024-04-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-23        hagjoon       최초 생성
 */
public interface JwtProperties {

    long EXPIRATION_TIME = 1000L * 60 * 60; // 1시간 Token 만료 (10분 설정)
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";

}
