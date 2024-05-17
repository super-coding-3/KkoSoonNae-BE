package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.Store;
import com.kkosoonnae.jpa.projection.StoreDetailViewProjection;
import com.kkosoonnae.jpa.projection.StoreListViewProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store,Integer> {
    @Query("SELECT new com.kkosoonnae.jpa.projection.StoreListViewProjection(s.storeNo,s.storeName,si.img, r.averageScope) " +
            "FROM Store s " +
            "LEFT JOIN StoreImg si ON s.storeNo = si.store.storeNo " +
            "LEFT JOIN Review r ON s.storeNo = r.store.storeNo " +
            "WHERE s.storeName LIKE :nameKeyword " +
            "OR s.address LIKE :addressKeyword ")
    List<StoreListViewProjection> findStoresByStoreNameInAndAddressInOrderByAddressAsc(String nameKeyword,String addressKeyword);

    Page<StoreListViewProjection> findAllByStoreNameLikeOrAddressLike(String nameKeyword,String addressKeyword,Pageable pageable);

    Optional<StoreDetailViewProjection> findStoreByStoreNo(Integer storeNo);

    @Query("SELECT s FROM Store s WHERE s.storeNo = :storeNo")
    Store findStoreNameByStoreNo(Integer storeNo);
}
