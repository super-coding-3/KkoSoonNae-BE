package com.kkosoonnae.customer.service;

import com.kkosoonnae.config.jwt.JwtTokenProvider;
import com.kkosoonnae.customer.dto.LoginDto;
import com.kkosoonnae.customer.dto.SignUpDto;
import com.kkosoonnae.jpa.entity.CustomerBas;
import com.kkosoonnae.jpa.entity.CustomerDtl;
import com.kkosoonnae.jpa.entity.RoleType;
import com.kkosoonnae.jpa.repository.CustomerBasRepository;
import com.kkosoonnae.jpa.repository.CustomerDtlRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * packageName    : com.kkosoonnae.member.service
 * fileName       : MemberService
 * author         : hagjoon
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        hagjoon       최초 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerBasRepository customerBasRepository;

    private final CustomerDtlRepository customerDtlRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;


    public boolean signUp(SignUpDto signUpDto){
        try{
            CustomerBas customerBas = CustomerBas.builder()
                    .loginId(signUpDto.getLoginId())
                    .email(signUpDto.getEmail())
                    .password(passwordEncoder.encode(signUpDto.getPassword()))
                    .cstmrDivCd(RoleType.USER)
                    .createDt(LocalDateTime.now())
                    .build();

            CustomerDtl customerDtl = CustomerDtl.builder()
                    .cstmrNo(customerBas.getCstmrNo())
                    .nickName(signUpDto.getNickName())
                    .phone(signUpDto.getPhone())
                    .zipCode(signUpDto.getZipCode())
                    .address(signUpDto.getAddress())
                    .addressDtl(signUpDto.getAddressDtl())
                    .build();

            customerBasRepository.save(customerBas);
            customerDtlRepository.save(customerDtl);

            return true;
        }catch (Exception e){
            log.error("회원 가입에 실패하였습니다. : {}",e);
            return false;
        }
    }

    public String login(LoginDto login){
        String loginId = login.getLoginId();
        String password = login.getPassword();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginId,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.createToken(loginId);
    }
}
