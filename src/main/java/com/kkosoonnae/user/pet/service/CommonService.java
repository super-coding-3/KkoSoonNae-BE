package com.kkosoonnae.user.pet.service;

import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.jpa.entity.Pet;
import com.kkosoonnae.jpa.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 * packageName    : com.kkosoonnae.pet.service
 * fileName       : CommonService
 * author         : hagjoon
 * date           : 2024-05-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-26        hagjoon       최초 생성
 */
@Service
@RequiredArgsConstructor
public class CommonService {

    private final PetRepository petRepository;

    public Pet getPet(PrincipalDetails principalDetails, Integer petNo){
        Pet pet = petRepository.findById(petNo)
                .orElseThrow(()-> new NoSuchElementException("PetNo = " + petNo));
        checkCstmr(principalDetails,pet);
        return pet;
    }

    private void checkCstmr(PrincipalDetails principalDetails, Pet pet){
        if(!pet.getCustomerBas().getCstmrNo().equals(principalDetails.getCustomerBas().getCstmrNo()))
      throw new AccessDeniedException("PetNo = " + pet.getPetNo());
    }
}
