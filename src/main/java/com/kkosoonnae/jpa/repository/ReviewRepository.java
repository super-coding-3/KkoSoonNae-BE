package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.Review;
import com.kkosoonnae.jpa.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Integer> {

    List<Review> findByStoreStoreNo(Integer storeId);

    Optional<Review> findTopByStoreStoreNoOrderByReviewDtDesc(Integer storeNo);
}
