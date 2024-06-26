package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.CustomerBas;
import com.kkosoonnae.jpa.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Query("SELECT p FROM Pet p WHERE p.customerBas.cstmrNo = :cstmrNo AND p.name = :petName")
    Pet findByCstmrNoAndPetNo(Integer cstmrNo, String petName);

    @Query("SELECT p FROM Pet p WHERE p.customerBas.cstmrNo = :cstmrNo")
    List<Pet> findByCstmrNo(Integer cstmrNo);

    @Query("SELECT p FROM Pet p WHERE p.petNo = :petNo")
    Pet findByPetNo(Integer petNo);

    @Modifying
    @Transactional
    @Query("UPDATE Pet p SET p.mainPet = 'N' WHERE p.customerBas.cstmrNo = :cstmrNo AND p.petNo <> :petNo")
    void resetMainPetForCustomer(Integer cstmrNo, Integer petNo);

    @Modifying
    @Transactional
    @Query("UPDATE Pet p SET p.mainPet = 'Y' WHERE p.petNo = :petNo")
    void setMainPet(Integer petNo);


    @Query("SELECT p FROM Pet p WHERE p.customerBas.cstmrNo = :cstmrNo AND p.mainPet = :y")
    Optional<Pet> findByCustomerNoAndMainPet(Integer cstmrNo, String y);

    boolean existsByCustomerBas(CustomerBas customerBas);

    @Query("SELECT p.img FROM Pet p WHERE p.petNo = :petNo")
    String findPetImgByPetNo(Integer petNo);
}
