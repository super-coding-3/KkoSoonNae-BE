package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.CustomerBas;
import com.kkosoonnae.jpa.entity.Review;
import com.kkosoonnae.jpa.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Integer> {

    List<Review> findByStoreStoreNo(Integer storeId);

    Optional<Review> findTopByStoreStoreNoOrderByReviewDtDesc(Integer storeNo);

    @Query("SELECT r.reviewNo, cb.cstmrNo, r.store, r.content, r.reviewDt " +
            "FROM Review r " +
            "LEFT JOIN CustomerBas cb ON r.cstmrNo = cb.cstmrNo " +
            "LEFT JOIN Store s ON r.store.storeNo = s.storeNo " +
            "WHERE cb.cstmrNo = :cstmrNo")
    Integer findCustomerByCustomerNumber(@Param("cstmrNo") Integer cstmrNo);

}
