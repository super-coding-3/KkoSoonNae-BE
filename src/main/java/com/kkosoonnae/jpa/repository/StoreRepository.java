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
    @Query("SELECT new com.kkosoonnae.jpa.projection.StoreListViewProjection(s.storeNo,s.storeName,s.roadAddress, si.img, AVG(r.scope)) " +
            "FROM Store s " +
            "LEFT JOIN FETCH StoreImg si ON s.storeNo = si.store.storeNo " +
            "LEFT JOIN FETCH Review r ON s.storeNo = r.store.storeNo " +
            "WHERE s.storeName LIKE :nameKeyword " +
            "OR s.roadAddress LIKE :addressKeyword " +
            "GROUP BY s.storeNo, si.img ")
    List<StoreListViewProjection> findStoresByStoreNameInAndAddressInOrderByAddressAsc(String nameKeyword,String addressKeyword);

    @Query("SELECT new com.kkosoonnae.jpa.projection.StoreDetailViewProjection(" +
            "s.storeNo, s.storeName, s.content, s.phone, s.roadAddress, " +
            "s.openingTime, s.closingTime,si.img ,AVG(r.scope), COUNT(ls.likeNo))" +
            "FROM Store s " +
            "LEFT JOIN FETCH StoreImg si ON s.storeNo = si.store.storeNo " +
            "LEFT JOIN FETCH  Review  r ON s.storeNo = r.store.storeNo " +
            "LEFT JOIN FETCH LikeStore ls ON s.storeNo = ls.store.storeNo " +
            "WHERE s.storeNo = :storeNo " +
            "GROUP BY s.storeNo,si.img ")
    Optional<StoreDetailViewProjection> findStoreByStoreNo(Integer storeNo);

    @Query("SELECT s FROM Store s WHERE s.storeNo = :storeNo")
    Store findStoreNameByStoreNo(Integer storeNo);

    @Query("SELECT s FROM Store s WHERE (6371 * acos(cos(radians(:lat)) * " +
            "cos(radians(s.lat)) * cos(radians(s.lon)-radians(:lon))+sin(radians(:lat)) * " +
            "sin(radians(s.lat))))< :distance")
    List<Store> findStoresWithinDistance(double lat, double lon, double distance);
}
