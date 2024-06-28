package com.kkosoonnae.president.info.service;

import com.kkosoonnae.common.exception.CustomException;
import com.kkosoonnae.common.exception.ErrorCode;
import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.config.jwt.JwtProperties;
import com.kkosoonnae.config.jwt.JwtTokenProvider;
import com.kkosoonnae.jpa.entity.CustomerBas;
import com.kkosoonnae.jpa.entity.CustomerDtl;
import com.kkosoonnae.jpa.entity.RoleType;
import com.kkosoonnae.jpa.entity.TermsAgreeTxn;
import com.kkosoonnae.jpa.repository.CustomerBasRepository;
import com.kkosoonnae.jpa.repository.CustomerDtlRepository;
import com.kkosoonnae.jpa.repository.TermsAgreeTxnRepository;
import com.kkosoonnae.president.info.dto.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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

    private final JwtTokenProvider jwtTokenProvider;

    private final UserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;


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
            for(TermRq agree : rq.getTerms()){
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

    private TermsAgreeTxn saveTermsAgreeTxn(TermRq agree, CustomerBas bas){
        TermsAgreeTxn termsAgreeTxn = TermsAgreeTxn.builder()
                .cstmrNo(bas.getCstmrNo())
                .termNo(agree.getTermNo())
                .agreeYn(agree.getAgreeYn())
                .agreeDt(LocalDateTime.now())
                .build();
        return termsAgreeTxnRepository.save(termsAgreeTxn);
    }

    public LoginRs login(LoginRq login) {
        String loginId = login.getLoginId();
        String password = login.getPassword();

        // authentication 을 기준으로 jwt token 생성
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginId, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // UserDetails 가져오기
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);
        CustomerBas customerBas = ((PrincipalDetails) userDetails).getCustomerBas();

        // CustomerDtl 정보 조회
        CustomerDtl customerDtl = customerDtlRepository.findByCstmrNo(customerBas.getCstmrNo());
        if (customerDtl == null) {
            throw new IllegalStateException("사용자의 세부 정보를 찾을 수 없습니다.");
        }

        // JWT 토큰 생성
        String token = jwtTokenProvider.createToken(loginId);
        String prefixedToken = JwtProperties.TOKEN_PREFIX + token;

        // LoginRsDto 생성 및 설정
        LoginRs loginRs = new LoginRs();
        loginRs.setCstmrNo(customerBas.getCstmrNo());
        loginRs.setLoginId(customerBas.getLoginId());
        loginRs.setNickName(customerDtl.getNickName());
        loginRs.setToken(prefixedToken);

        return loginRs;
    }

    @Transactional
    public InfoUpdateRs updateInfo(Integer cstmrNo,InfoUpdateRq rq){
        Optional<CustomerBas> customerBas = customerBasRepository.findById(cstmrNo);

        if(customerBas.isEmpty()){
            throw new CustomException(ErrorCode.USER_NOT_LOGIN);
        }
        CustomerBas existingBas = customerBas.get();
        CustomerBas bas = this.updateCustomerBas(existingBas,rq);

        CustomerDtl dtl = this.updateCustomerDtl(bas,rq);

        InfoUpdateRs rs = new InfoUpdateRs();
        rs.setLoginId(customerBas.get().getLoginId());
        rs.setName(dtl.getNickName());
        rs.setEmail(bas.getEmail());
        rs.setPhone(dtl.getPhone());

        return rs;
    }

    private CustomerBas updateCustomerBas(CustomerBas bas,InfoUpdateRq rq){
        CustomerBas customerBas = CustomerBas.builder()
                .loginId(bas.getLoginId())
                .password(bas.getPassword())
                .email(rq.getEmail())
                .createDt(bas.getCreateDt())
                .cstmrDivCd(bas.getCstmrDivCd())
                .build();
        return customerBasRepository.save(customerBas);
    }

    private CustomerDtl updateCustomerDtl(CustomerBas bas,InfoUpdateRq rq){
        CustomerDtl customerDtl = CustomerDtl.builder()
                .customerBas(bas)
                .nickName(rq.getName())
                .phone(rq.getPhone())
                .build();
        return customerDtlRepository.save(customerDtl);
    }
}
