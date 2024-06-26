package com.kkosoonnae.president.info.service;

import com.kkosoonnae.jpa.entity.CustomerBas;
import com.kkosoonnae.jpa.entity.CustomerDtl;
import com.kkosoonnae.jpa.entity.RoleType;
import com.kkosoonnae.jpa.repository.CustomerBasRepository;
import com.kkosoonnae.jpa.repository.CustomerDtlRepository;
import com.kkosoonnae.president.info.dto.SignUpDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * packageName    : com.kkosoonnae.president.info.service
 * fileName       : InfoService
 * author         : hagjoon
 * date           : 2024-06-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-14        hagjoon       최초 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class InfoService {

    private final PasswordEncoder passwordEncoder;

    private final CustomerBasRepository customerBasRepository;

    private final CustomerDtlRepository customerDtlRepository;


    public boolean signUp(SignUpDto signUpDto){
        CustomerBas customerBas = CustomerBas.builder()
                .loginId(signUpDto.getLoginId())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .email(signUpDto.getEmail())
                .cstmrDivCd(RoleType.PRESIDENT)
                .createDt(LocalDateTime.now())
                .build();

        customerBasRepository.save(customerBas);

        CustomerDtl customerDtl = CustomerDtl.builder()
                .customerBas(customerBas)
                .nickName(signUpDto.getNickName())
                .phone(signUpDto.getPhone())
                .build();
        customerDtlRepository.save(customerDtl);

        return true;
    }
}
