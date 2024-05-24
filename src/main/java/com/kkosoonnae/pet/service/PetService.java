package com.kkosoonnae.pet.service;

import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.jpa.entity.*;
import com.kkosoonnae.jpa.repository.PetQueryRepository;
import com.kkosoonnae.jpa.repository.PetRepository;
import com.kkosoonnae.pet.dto.PetInfoDto;
import com.kkosoonnae.pet.dto.PetListRqDto;
import com.kkosoonnae.pet.dto.PetUpdate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    private final PetQueryRepository query;

    public List<PetInfoDto> petList(){
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer cstmrNo = principalDetails.getCustomerBas().getCstmrNo();

        List<PetInfoDto> petList = query.listPet(cstmrNo);

        if (petList == null || petList.isEmpty()) {
            return Collections.emptyList();
        }

        return petList;
    }

    public PetInfoDto detailPet(Integer petNo){
        PetInfoDto result = query.findPetInfoById(petNo);
        return result;
    }

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

    @Transactional
    public void petUpdate(PrincipalDetails principalDetails,Integer petNo,PetUpdate petUpdate){
        Integer cstmrNo = principalDetails.getCustomerBas().getCstmrNo();
            Pet pet = petRepository.findById(petNo)
                    .orElseThrow(()-> new IllegalStateException("반려동물에 대한 정보를 찾을 수 없습니다."));

            if(!pet.getCustomerBas().getCstmrNo().equals(cstmrNo)) {
                throw new IllegalArgumentException("본인의 반려동물 정보만 수정 가능합니다.");
            }

            pet.updatePet(petUpdate);


            petRepository.save(pet);

        }
    @Transactional
    public void deletePet(Integer cstmrNo,Integer petNo){
        boolean exists = query.existsByCstmrNoAndPetNo(cstmrNo, petNo);

        if(!exists){
            throw new IllegalArgumentException("해당 회원의 반려 동물 정보가 없습니다.");
        }
        query.deletePet(petNo);
    }

}
