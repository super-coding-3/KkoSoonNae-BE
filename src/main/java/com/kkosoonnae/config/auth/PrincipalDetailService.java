package com.kkosoonnae.config.auth;

import com.kkosoonnae.jpa.entity.CustomerBas;
import com.kkosoonnae.jpa.repository.CustomerBasRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * packageName    : com.kurly.api.config.auth
 * fileName       : PrincipalDetailService
 * author         : hagjoon
 * date           : 2024-04-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-23        hagjoon       최초 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final CustomerBasRepository repository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        return repository.findByLoginId(loginId)
                .map(customerBas -> createuserDetails(customerBas))
                .orElseThrow(()-> new UsernameNotFoundException(loginId + "존재하지 않는 loginId 입니다."));
    }

    // DB에서 조회한 user 정보를 기반으로 UserDetails의 구현체인
    // User (org.springframework.security.core.userdetails.User) 를 생성하여 return 한다.
    private UserDetails createuserDetails(CustomerBas customerBas){
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(customerBas.getCstmrDivCd().toString());

        return new org.springframework.security.core.userdetails.User(
                customerBas.getLoginId(),
                customerBas.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}
