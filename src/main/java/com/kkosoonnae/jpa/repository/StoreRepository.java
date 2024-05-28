package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.Store;
import com.kkosoonnae.jpa.projection.MainStoresListviewProjection;
import com.kkosoonnae.jpa.projection.StoreDetailViewProjection;
import com.kkosoonnae.jpa.projection.StoreListViewProjection;
import com.kkosoonnae.jpa.projection.StoreReviewsViewProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store,Integer> {
    @Query("SELECT new com.kkosoonnae.jpa.projection.StoreListViewProjection(s.storeNo,s.storeName,s.roadAddress, si.img, AVG(r.scope)) " +
            "FROM Store s " +
            "LEFT JOIN FETCH StoreImg si ON s.storeNo = si.store.storeNo " +
            "LEFT JOIN FETCH Review r ON s.storeNo = r.store.storeNo " +
            "WHERE s.storeName LIKE %:nameAddressKeyword% " +
            "OR s.roadAddress LIKE %:nameAddressKeyword% " +
            "GROUP BY s.storeNo, si.img ")
    List<StoreListViewProjection> findStoresByStoreNameInAndAddressInOrderByAddressAsc(String nameAddressKeyword);

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

    @Query("SELECT new com.kkosoonnae.jpa.projection.MainStoresListviewProjection(s.storeNo,s.storeName,s.roadAddress, AVG(r.scope)) " +
            "FROM Store s " +
            "LEFT JOIN FETCH Review r ON s.storeNo = r.store.storeNo " +
            "WHERE s.roadAddress LIKE %:addressKeyword% " +
            "GROUP BY s.storeNo " )
    List<MainStoresListviewProjection> findMainStores(String addressKeyword, Pageable pageable);
    @Query("SELECT new com.kkosoonnae.jpa.projection.StoreReviewsViewProjection(" +
            "s.storeNo,s.storeName, si.img, COUNT(ls.likeNo), " +
            "r.reviewNo,r.cstmrNo.cstmrNo,r.content,r.scope,cd.nickName,p.img) " +
            "FROM Store s " +
            "LEFT JOIN StoreImg si ON s.storeNo = si.store.storeNo " +
            "LEFT JOIN LikeStore ls ON s.storeNo = ls.store.storeNo " +
            "LEFT JOIN Review r ON s.storeNo = r.store.storeNo " +
            "LEFT JOIN CustomerDtl cd ON r.cstmrNo.cstmrNo = cd.customerBas.cstmrNo " +
            "LEFT JOIN Pet p ON cd.cstmrNo = p.customerBas.cstmrNo " +
            "WHERE s.storeNo = :storeNo " +
            "GROUP BY s.storeNo,s.storeName,si.img,r.reviewNo,r.cstmrNo,r.content,r.scope,cd.nickName,p.img")
    List<StoreReviewsViewProjection> findByReviews(Integer storeNo);
}
