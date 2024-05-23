package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.CustomerAvail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAvailRepository extends JpaRepository<CustomerAvail, Integer> {
}
