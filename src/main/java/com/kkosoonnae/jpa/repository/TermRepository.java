package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.kkosoonnae.jpa.repository
 * fileName       : TermRespository
 * author         : hagjoon
 * date           : 2024-06-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-24        hagjoon       최초 생성
 */
@Repository
public interface TermRepository extends JpaRepository<Term,Integer> {
}
