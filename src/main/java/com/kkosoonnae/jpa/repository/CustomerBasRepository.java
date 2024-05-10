package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.CustomerBas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * packageName    : com.kkosoonnae.jpa.repository
 * fileName       : CustomerRepository
 * author         : hagjoon
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        hagjoon       최초 생성
 */
@Repository
public interface CustomerBasRepository extends JpaRepository<CustomerBas,Integer> {
    Optional<CustomerBas> findByLoginId(String loginId);
}
