package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.ReservedPets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservedPetsRepository extends JpaRepository<ReservedPets, Integer> {
}
