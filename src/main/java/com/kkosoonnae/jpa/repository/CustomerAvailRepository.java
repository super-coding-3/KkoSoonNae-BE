package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.CustomerAvail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.kkosoonnae.jpa.repository
 * fileName       : CustomerAvailRepository
 * author         : hagjoon
 * date           : 2024-05-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-23        hagjoon       최초 생성
 */
public interface CustomerAvailRepository extends JpaRepository<CustomerAvail,Integer> {
}
