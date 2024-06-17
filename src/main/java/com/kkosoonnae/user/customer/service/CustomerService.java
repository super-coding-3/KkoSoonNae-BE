package com.kkosoonnae.user.customer.service;

import com.kkosoonnae.config.jwt.TokenProvider;
import com.kkosoonnae.jpa.entity.CustomerBas;
import com.kkosoonnae.jpa.entity.CustomerDtl;
import com.kkosoonnae.jpa.entity.RoleType;
import com.kkosoonnae.config.auth.PrincipalDetails;
//import com.kkosoonnae.config.jwt.JwtTokenProvider;
import com.kkosoonnae.jpa.repository.CustomerBasRepository;
import com.kkosoonnae.jpa.repository.CustomerDtlRepository;
import com.kkosoonnae.user.customer.dto.InfoDto;
import com.kkosoonnae.user.customer.dto.LoginDto;
import com.kkosoonnae.user.customer.dto.SignUpDto;
import com.kkosoonnae.user.customer.dto.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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

//    private final JwtTokenProvider jwtTokenProvider;

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

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
                    .cstmrDivCd(RoleType.ROLE_USER)
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

    public TokenResponseDto login(LoginDto login){

        // username, password를 파라미터로 받고 이를 이용해 UsernamePasswordAuthenticationToken을 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(login.getLoginId(),login.getPassword());

        // authenticationToken을 이용해서 Authenticaiton 객체를 생성하려고 authenticate 메소드가 실행될 때
        // CustomUserDetailsService에서 override한 loadUserByUsername 메소드가 실행된다.
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // authentication 을 기준으로 jwt token 생성
        String jwt = tokenProvider.createToken(authentication);

        return new TokenResponseDto(jwt);
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
