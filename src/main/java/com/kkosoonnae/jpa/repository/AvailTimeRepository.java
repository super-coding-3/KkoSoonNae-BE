package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.AvailTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailTimeRepository extends JpaRepository<AvailTime, Integer> {

    @Query("SELECT at.availNo FROM AvailTime at WHERE at.store.storeNo = :storeNo")
    Integer findByStoreNo(Integer storeNo);

//    @Query("SELECT at FROM AvailTime at WHERE at.store.storeNo = :storeNo")
//    AvailTime findAvailTimeByStoreNo(Integer storeNo);

    @Query("SELECT at FROM AvailTime at WHERE at.store.storeNo = :storeNo")
    AvailTime findAvailTimeByStoreNo(Integer storeNo);
}
