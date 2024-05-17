package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.QCustomerBas;
import com.kkosoonnae.jpa.entity.QPet;
import com.kkosoonnae.pet.dto.PetInfoDto;
import com.kkosoonnae.pet.dto.PetListRqDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName    : com.kkosoonnae.jpa.repository
 * fileName       : PetQueryRepositroy
 * author         : hagjoon
 * date           : 2024-05-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-16        hagjoon       최초 생성
 */
@RequiredArgsConstructor
@Repository
@Slf4j
public class PetQueryRepository {

    private final JPAQueryFactory query;

    public List<PetInfoDto> listPet(Integer cstmrNo) {
        QPet pet = QPet.pet;
        return query
                .select(Projections.bean(PetInfoDto.class,
                        pet.img,
                        pet.name,
                        pet.type,
                        pet.birthDt,
                        pet.gender,
                        pet.weight))
                .from(pet)
                .where(pet.customerBas.cstmrNo.eq(cstmrNo))  // 해당 회원의 cstmrNo와 일치하는 반려동물 정보 조회
                .fetch();
    }

    public PetInfoDto findPetInfoById(Integer petNo) {
        QPet pet = QPet.pet;

        return query.select(Projections.constructor(PetInfoDto.class,
                        pet.img,
                        pet.name,
                        pet.type,
                        pet.birthDt,
                        pet.gender,
                        pet.weight))
                .from(pet)
                .where(pet.petNo.eq(petNo))
                .fetchOne();
    }
}
