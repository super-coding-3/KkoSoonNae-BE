package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.CustomerDtl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.kkosoonnae.jpa.repository
 * fileName       : CustomerDtlRepository
 * author         : hagjoon
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        hagjoon       최초 생성
 */
@Repository
public interface CustomerDtlRepository extends JpaRepository<CustomerDtl,Integer> {
}
