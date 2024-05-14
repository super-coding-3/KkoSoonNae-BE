package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.AvailTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailTimeRepository extends JpaRepository<AvailTime, Integer> {

    @Query("SELECT at.availNo FROM AvailTime at WHERE at.storeNo = :storeNo")
    Integer findByStoreNo(Integer storeNo);
}
