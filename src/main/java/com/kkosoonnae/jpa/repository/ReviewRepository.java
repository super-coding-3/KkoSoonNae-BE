package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.Review;
import com.kkosoonnae.jpa.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Integer> {

    @Query("SELECT * " +
            "FROM REVIEW r " +
            "LEFT JOIN CUSTOMER_BAS cb" +
            "ON r.CSTMR_NO = cb.CSTMR_NO" +
            "LEFT JOIN STORE s " +
            "ON r.STORE_NO = s.STORE_NO")
    CustomerBas findCustomerByCustomerNumber(@Param("cstmrNo") Integer cstmrNo);

    List<Review> findByStoreStoreNo(Integer storeId);

    Optional<Review> findTopByStoreStoreNoOrderByReviewDtDesc(Integer storeNo);
}
