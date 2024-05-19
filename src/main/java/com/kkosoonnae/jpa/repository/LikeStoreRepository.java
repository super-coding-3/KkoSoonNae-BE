package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.LikeStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeStoreRepository extends JpaRepository<LikeStore,Integer> {


    Optional<LikeStore> findLikeStoreByCustomerBas_CstmrNoAndStoreStoreNo (Integer customerId, Integer storeId);

    Optional<LikeStore> deleteLikeStoreByStoreStoreNo (Integer storeNo);

    List<LikeStore> countLikeStoreByCustomerBas_CstmrNo (Integer customerId);
}
