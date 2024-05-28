package com.kkosoonnae.pet.service;

//import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.kkosoonnae.config.auth.PrincipalDetails;
//import com.kkosoonnae.config.s3.S3Uploader;
import com.kkosoonnae.config.s3.S3Uploader;
import com.kkosoonnae.jpa.entity.*;
import com.kkosoonnae.jpa.repository.PetQueryRepository;
import com.kkosoonnae.jpa.repository.PetRepository;
import com.kkosoonnae.pet.dto.PetAddDto;
import com.kkosoonnae.pet.dto.PetInfoDto;
import com.kkosoonnae.pet.dto.PetUpdate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

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

    private final S3Uploader s3Uploader;

    private final CommonService commonService;

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

    public void petAdd(PrincipalDetails principalDetails, PetAddDto petAddDto) {
        petRepository.save(petAddDto.addPet(principalDetails));
    }

    @Transactional
    public void petUpdate(PrincipalDetails principalDetails,Integer petNo,PetUpdate petUpdate,MultipartFile file){
        Pet pet = commonService.getPet(principalDetails,petNo);
        String img = pet.getImg();
        if(file != null){
            try{
                img = s3Uploader.upload(file,"pet");
            }catch (IOException e){
                throw new AmazonS3Exception("file = " + file.getOriginalFilename());
            }
        }
        pet.updatePet(petUpdate,img);
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

    public void mainPet(Integer petNo,Integer cstmrNo){
        Pet pet = petRepository.findById(petNo)
                .orElseThrow(()-> new NotFoundException("해당 반려동물을 찾을 수 없습니다."));

        if (!pet.getCustomerBas().getCstmrNo().equals(cstmrNo)) {
            throw new IllegalArgumentException("해당 고객의 반려동물이 아닙니다.");
        }

        petRepository.resetMainPetForCustomer(cstmrNo,petNo);
        petRepository.setMainPet(petNo);
    }



    public String getMainPetImageByCustomerNo(CustomerBas customerBas) {
        return petRepository.findByCustomerNoAndMainPet(customerBas.getCstmrNo(), "Y")
                .map(Pet::getImg)
                .orElse(null);
    }
}
