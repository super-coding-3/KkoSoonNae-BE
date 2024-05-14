package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StyleRepository extends JpaRepository<Style,Integer> {

    List<Style> findByStoreNo (Integer storeNo);
}
