package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.Store;
import com.kkosoonnae.jpa.projection.StoreDetailViewProjection;
import com.kkosoonnae.jpa.projection.StoreListViewProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store,Integer> {

    Optional<StoreListViewProjection> findListViewByAddressContaining(String addressKeyword);

    Optional<StoreDetailViewProjection> findStoreByStoreNo(Integer storeNo);

    @Query("SELECT s FROM Store s WHERE s.storeNo = :storeNo")
    Store findStoreNameByStoreNo(Integer storeNo);
}
