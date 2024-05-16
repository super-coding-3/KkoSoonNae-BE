package com.kkosoonnae.pet.service;

import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.jpa.entity.*;
import com.kkosoonnae.jpa.repository.PetRepository;
import com.kkosoonnae.pet.dto.PetInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
public class PetService {


    private final PetRepository petRepository;

    public void petAdd(PrincipalDetails principalDetails, PetInfoDto petInfoDto){
        CustomerBas customerBas = principalDetails.getCustomerBas();

//        CustomerBas customerBas = customerBasRepository.findById(cstmrNo)
//                .orElseThrow(()-> new NotFoundException("Customer not found with cstmrNo : " + cstmrNo));

        Pet pet = Pet.builder()
                .customerBas(customerBas)
                .img(petInfoDto.getImg())
                .name(petInfoDto.getName())
                .type(petInfoDto.getType())
                .birthDt(petInfoDto.getBirthDt())
                .gender(petInfoDto.getGender())
                .weight(petInfoDto.getWeight())
                .build();

        petRepository.save(pet);
    }

    public void petUpdate(PrincipalDetails principalDetails, PetInfoDto petInfoDto){
        CustomerBas cstmrNo = principalDetails.getCustomerBas();

        Pet pet = Pet.builder().customerBas(cstmrNo)
                .img(petInfoDto.getImg())
                .name(petInfoDto.getName())
                .type(petInfoDto.getType())
                .birthDt(petInfoDto.getBirthDt())
                .gender(petInfoDto.getGender())
                .weight(petInfoDto.getWeight())
                .build();

        petRepository.save(pet);

    }
    public void deletePet(Integer petNo){
        Pet pet = petRepository.findById(petNo).orElseThrow(()-> new IllegalStateException("반려동물에 대한 정보를 찾을 수 없습니다."));

        if(pet != null){
            petRepository.deleteById(petNo);
        }
    }

}
