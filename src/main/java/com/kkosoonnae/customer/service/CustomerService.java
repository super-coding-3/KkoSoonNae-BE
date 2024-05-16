package com.kkosoonnae.customer.service;

import com.kkosoonnae.config.auth.PrincipalDetailService;
import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.config.jwt.JwtTokenProvider;
import com.kkosoonnae.customer.dto.*;
import com.kkosoonnae.jpa.entity.*;
import com.kkosoonnae.jpa.repository.CustomerBasRepository;
import com.kkosoonnae.jpa.repository.CustomerDtlRepository;
import com.kkosoonnae.jpa.repository.PetRepository;
import com.kkosoonnae.jpa.repository.QnaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.nio.file.attribute.UserPrincipalNotFoundException;
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

    private final QnaRepository qnaRepository;

    private final PetRepository petRepository;

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

    public InfoDto getUserProfile(PrincipalDetails principalDetails){
        //PrincipalDetails를 이용해 로그인한 사용자 기본 정보 조회
        CustomerBas customerBas = principalDetails.getCustomerBas();

        CustomerDtl customerDtl = customerDtlRepository.findByCustomerBas(customerBas)
                .orElseThrow(() -> new IllegalStateException("사용자의 상세 정보를 찾을 수 없습니다."));

        InfoDto infoDto = new InfoDto();
        infoDto.setNickName(customerDtl.getNickName());
        infoDto.setPhone(customerDtl.getPhone());
        infoDto.setZipCode(customerDtl.getZipCode());
        infoDto.setAddress(customerDtl.getAddress());
        infoDto.setAddressDtl(customerDtl.getAddressDtl());

        return infoDto;
    }

    public void updateUserProfile(InfoDto infoDto){
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomerBas customerBas = principalDetails.getCustomerBas();

        CustomerDtl customerDtl = customerDtlRepository.findByCustomerBas(customerBas)
                .orElseThrow(() -> new IllegalStateException("사용자의 상세 정보를 찾을 수 없습니다."));

        if(customerDtl != null){
            customerDtl = CustomerDtl.builder()
                    .nickName(infoDto.getNickName())
                    .phone(infoDto.getPhone())
                    .zipCode(infoDto.getZipCode())
                    .address(infoDto.getAddress())
                    .addressDtl(infoDto.getAddressDtl())
                    .build();
        }
        customerDtlRepository.save(customerDtl);

    }

    public String getUserNickname() {
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomerBas customerBas = principalDetails.getCustomerBas();

        CustomerDtl customerDtl = customerBas.getCustomerDtl();
        if (customerDtl == null) {
            throw new IllegalStateException("사용자의 상세 정보를 찾을 수 없습니다.");
        }

        return customerDtl.getNickName();
    }

    public boolean createQna(QnaDto qnaDto,PrincipalDetails principalDetails){
        Integer cstmrNo = principalDetails.getCustomerBas().getCstmrNo();
        try{
            Qna qna = Qna.builder()
                    .cstmrNo(cstmrNo)
                    .title(qnaDto.getTitle())
                    .content(qnaDto.getContent())
                    .build();
            qnaRepository.save(qna);
            return true;
        }catch (Exception e){
            log.error("문의 사항 등록에 실패하였습니다. {}",e);
            return false;
        }

    }


}
