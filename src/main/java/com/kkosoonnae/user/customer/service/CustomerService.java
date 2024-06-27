package com.kkosoonnae.user.customer.service;

import com.kkosoonnae.common.exception.CustomException;
import com.kkosoonnae.common.exception.ErrorCode;
import com.kkosoonnae.config.jwt.JwtProperties;
import com.kkosoonnae.config.jwt.JwtTokenProvider;
import com.kkosoonnae.jpa.entity.CustomerBas;
import com.kkosoonnae.jpa.entity.CustomerDtl;
import com.kkosoonnae.jpa.entity.RoleType;
import com.kkosoonnae.config.auth.PrincipalDetails;
//import com.kkosoonnae.config.jwt.JwtTokenProvider;
import com.kkosoonnae.jpa.entity.TermsAgreeTxn;
import com.kkosoonnae.jpa.repository.CustomerBasRepository;
import com.kkosoonnae.jpa.repository.CustomerDtlRepository;
import com.kkosoonnae.jpa.repository.TermsAgreeTxnRepository;
import com.kkosoonnae.user.customer.dto.*;
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

    private final TermsAgreeTxnRepository termsAgreeTxnRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;


    public boolean signUp(SignUpDto signUpDto) {
        // 1. 아이디, 닉네임 중복 확인
        if(customerBasRepository.existsByLoginId(signUpDto.getLoginId())){
            throw new CustomException(ErrorCode.DUPLICATE_LOGIN);
        } if (customerDtlRepository.existsByNickName(signUpDto.getNickName())) {
            throw new CustomException(ErrorCode.DUPLICATE_NICKNAME);
        }

        // 2. 기본 정보 저장
        CustomerBas bas = this.saveCustomerBas(signUpDto);

        // 3. 상세 정보 저장
        this.saveCustomerDtl(signUpDto,bas);

        // 4. 약관 동의 정보 저장
        if(signUpDto.getTerms() != null){
            for(TermDto agree : signUpDto.getTerms()){
                this.saveTermsAgreeTxn(agree,bas);
            }
        }

            return true;
    }

    private CustomerBas saveCustomerBas(SignUpDto rq){
        CustomerBas customerBas = CustomerBas.builder()
                .loginId(rq.getLoginId())
                .email(rq.getEmail())
                .password(passwordEncoder.encode(rq.getPassword()))
                .cstmrDivCd(RoleType.USER)
                .createDt(LocalDateTime.now())
                .build();

        return customerBasRepository.save(customerBas);
    }

    private CustomerDtl saveCustomerDtl(SignUpDto rq, CustomerBas bas){
        CustomerDtl customerDtl = CustomerDtl.builder()
                .customerBas(bas)
                .nickName(rq.getNickName())
                .phone(rq.getPhone())
                .zipCode(rq.getZipCode())
                .address(rq.getAddress())
                .addressDtl(rq.getAddressDtl())
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

    public boolean existsLoginId(String loginId){
        return customerBasRepository.existsByLoginId(loginId);
    }

    public boolean existsNickName(String nickName){
        return customerDtlRepository.existsByNickName(nickName);
    }

    public LoginRsDto login(LoginDto login) {
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
        LoginRsDto loginRsDto = new LoginRsDto();
        loginRsDto.setCstmrNo(customerBas.getCstmrNo());
        loginRsDto.setLoginId(customerBas.getLoginId());
        loginRsDto.setNickName(customerDtl.getNickName());
        loginRsDto.setToken(prefixedToken);

        return loginRsDto;
    }

    public InfoDto getUserProfile(Integer cstmrNo){
        CustomerDtl customerDtl = customerDtlRepository.findByCstmrNo(cstmrNo);

        if (customerDtl == null) {
            throw new IllegalStateException("사용자의 정보를 찾을 수 없습니다.");
        }

        InfoDto infoDto = new InfoDto();
        infoDto.setNickName(customerDtl.getNickName());
        infoDto.setPhone(customerDtl.getPhone());
        infoDto.setZipCode(customerDtl.getZipCode());
        infoDto.setAddress(customerDtl.getAddress());
        infoDto.setAddressDtl(customerDtl.getAddressDtl());

        return infoDto;
    }

    public void updateUserProfile(InfoDto infoDto,Integer cstmrNo){
        CustomerDtl customerDtl = customerDtlRepository.findByCstmrNo(cstmrNo);

        if (customerDtl == null) {
            throw new IllegalStateException("사용자의 정보를 찾을 수 없습니다.");
        }

        customerDtl.updateCustomer(infoDto);

        customerDtlRepository.save(customerDtl);

    }

    public String getUserNickname(Integer cstmrNo) {
        CustomerDtl customerDtl = customerDtlRepository.findByCstmrNo(cstmrNo);

        if (customerDtl == null) {
            throw new IllegalStateException("사용자의 상세 정보를 찾을 수 없습니다.");
        }

        return customerDtl.getNickName();
    }

}
