package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
}
