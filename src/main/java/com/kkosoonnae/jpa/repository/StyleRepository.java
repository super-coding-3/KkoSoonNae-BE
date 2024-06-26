package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StyleRepository extends JpaRepository<Style,Integer> {
    @Query("SELECT s FROM Style s LEFT JOIN FETCH s.store WHERE s.store.storeNo = :storeNo")
    List<Style> findByStoreNo (Integer storeNo);

    @Query("SELECT s FROM Style s WHERE s.store.storeNo = :storeNo")
    List<Style> findStylNameByStoreNo(Integer storeNo);

    @Query("SELECT s FROM Style s WHERE s.store.storeNo = :storeNo AND s.styleName = :cutStyle")
    Style findByStoreNoAndStyleName(Integer storeNo, String cutStyle);

    List<Style> findByStyleName(String styleName);
}
