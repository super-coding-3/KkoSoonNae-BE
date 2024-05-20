package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.kkosoonnae.jpa.repository
 * fileName       : PointRepository
 * author         : hagjoon
 * date           : 2024-05-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-20        hagjoon       최초 생성
 */
@Repository
public interface PointRepository extends JpaRepository<Point,Integer> {
}
