package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.Pet;
import com.kkosoonnae.pet.dto.PetInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.kkosoonnae.jpa.repository
 * fileName       : PetRepository
 * author         : hagjoon
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        hagjoon       최초 생성
 */
@Repository
public interface PetRepository extends JpaRepository<Pet,Integer> {

    @Query("SELECT p.type, p.weight FROM Pet p WHERE p.customerBas = :cstmrNo")
    List<Pet> findByCustomerNo(Integer cstmrNo);

    @Query("SELECT p FROM Pet p WHERE p.customerBas.cstmrNo = :cstmrNo AND p.name = :petName")
    Pet findByCstmrNoAndPetNo(Integer cstmrNo, String petName);

    @Query("SELECT p FROM Pet p WHERE p.customerBas.cstmrNo = :cstmrNo")
    List<Pet> findByCstmrNo(Integer cstmrNo);


    @Query("SELECT p FROM Pet p WHERE p.petNo = :petNo")
    Pet findByPetNo(Integer petNo);
}
