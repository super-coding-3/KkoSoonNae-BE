package com.kkosoonnae.president.info.service;

import com.kkosoonnae.common.exception.CustomException;
import com.kkosoonnae.common.exception.ErrorCode;
import com.kkosoonnae.jpa.entity.CustomerBas;
import com.kkosoonnae.jpa.entity.CustomerDtl;
import com.kkosoonnae.jpa.entity.RoleType;
import com.kkosoonnae.jpa.entity.TermsAgreeTxn;
import com.kkosoonnae.jpa.repository.CustomerBasRepository;
import com.kkosoonnae.jpa.repository.CustomerDtlRepository;
import com.kkosoonnae.jpa.repository.TermsAgreeTxnRepository;
import com.kkosoonnae.president.info.dto.SignUpDto;
import com.kkosoonnae.user.customer.dto.TermDto;
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

    private final TermsAgreeTxnRepository termsAgreeTxnRepository;


    public boolean signUp(SignUpDto rq){

        // 1. 아이디 중복 확인
        if(customerBasRepository.existsByLoginId(rq.getLoginId())){
            throw new CustomException(ErrorCode.DUPLICATE_LOGIN);
        }

        // 2. 기본 정보 저장
        CustomerBas bas = this.saveCustomerBas(rq);

        // 3. 상세 정보 저장
        this.saveCustomerDtl(rq,bas);

        // 4. 약관 동의 정보 저장
        if(rq.getTerms() != null) {
            for(TermDto agree : rq.getTerms()){
                this.saveTermsAgreeTxn(agree,bas);
            }
        }

        return true;
    }

    private CustomerBas saveCustomerBas(SignUpDto rq){
        CustomerBas customerBas = CustomerBas.builder()
                .loginId(rq.getLoginId())
                .password(passwordEncoder.encode(rq.getPassword()))
                .email(rq.getEmail())
                .cstmrDivCd(RoleType.PRESIDENT)
                .createDt(LocalDateTime.now())
                .build();

       return customerBasRepository.save(customerBas);
    }

    private CustomerDtl saveCustomerDtl(SignUpDto rq, CustomerBas bas){
        CustomerDtl customerDtl = CustomerDtl.builder()
                .customerBas(bas)
                .nickName(rq.getNickName())
                .phone(rq.getPhone())
                .build();
        return customerDtlRepository.save(customerDtl);
    }

    private TermsAgreeTxn saveTermsAgreeTxn(TermDto agree, CustomerBas bas){
        TermsAgreeTxn termsAgreeTxn = TermsAgreeTxn.builder()
                .cstmrNo(bas.getCstmrNo())
                .termNo(agree.getTermNo())
                .agreeYn(agree.getAgreeYn())
                .agreeDt(LocalDateTime.now())
                .build();
        return termsAgreeTxnRepository.save(termsAgreeTxn);
    }
}
