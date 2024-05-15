package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.StoreImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreImgRepository extends JpaRepository<StoreImg, Integer> {

    @Query("SELECT si FROM StoreImg si WHERE si.storeNo = :storeNo")
    Optional<StoreImg> findByStoreNo (Integer storeNo);
}