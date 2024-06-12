package com.kkosoonnae.user.customer.service;

import com.kkosoonnae.jpa.entity.CustomerBas;
import com.kkosoonnae.jpa.entity.CustomerDtl;
import com.kkosoonnae.jpa.entity.RoleType;
import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.config.jwt.JwtTokenProvider;
import com.kkosoonnae.jpa.repository.CustomerBasRepository;
import com.kkosoonnae.jpa.repository.CustomerDtlRepository;
import com.kkosoonnae.user.customer.dto.InfoDto;
import com.kkosoonnae.user.customer.dto.LoginDto;
import com.kkosoonnae.user.customer.dto.SignUpDto;
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


    public boolean signUp(SignUpDto signUpDto) {
        if (existsLoginId(signUpDto.getLoginId())) {
            throw new IllegalArgumentException("중복된 아이디 입니다.");
        } if (existsNickName(signUpDto.getNickName())) {
            throw new IllegalArgumentException("중복된 닉네임 입니다.");
        }
            CustomerBas customerBas = CustomerBas.builder()
                    .loginId(signUpDto.getLoginId())
                    .email(signUpDto.getEmail())
                    .password(passwordEncoder.encode(signUpDto.getPassword()))
                    .cstmrDivCd(RoleType.USER)
                    .createDt(LocalDateTime.now())
                    .build();

            customerBasRepository.save(customerBas);

            CustomerDtl customerDtl = CustomerDtl.builder()
                    .customerBas(customerBas)
                    .nickName(signUpDto.getNickName())
                    .phone(signUpDto.getPhone())
                    .zipCode(signUpDto.getZipCode())
                    .address(signUpDto.getAddress())
                    .addressDtl(signUpDto.getAddressDtl())
                    .build();

            customerDtlRepository.save(customerDtl);
            return true;
    }

    public boolean existsLoginId(String loginId){
        return customerBasRepository.existsByLoginId(loginId);
    }

    public boolean existsNickName(String nickName){
        return customerDtlRepository.existsByNickName(nickName);
    }

    public String login(LoginDto login){
        String loginId = login.getLoginId();
        String password = login.getPassword();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginId,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.createToken(loginId);
    }

    public InfoDto getUserProfile(PrincipalDetails principalDetails){
        //PrincipalDetails를 이용해 로그인한 사용자 기본 정보 조회
        CustomerBas customerBas = principalDetails.getCustomerBas();

        Integer cstmrNo = customerBas.getCstmrNo();

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
