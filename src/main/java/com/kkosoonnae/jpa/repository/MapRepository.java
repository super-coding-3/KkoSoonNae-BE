package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapRepository extends JpaRepository<Store, Integer> {
}
