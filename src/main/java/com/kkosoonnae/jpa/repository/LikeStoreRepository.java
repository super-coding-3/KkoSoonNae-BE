package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.LikeStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeStoreRepository extends JpaRepository<LikeStore,Integer> {
    List<LikeStore> countLikeStoreByStoreStoreNo (Integer customerId);
    boolean existsLikeStoreByCustomerBas_CstmrNoAndStore_StoreNo(Integer customerNo, Integer storeNo);
    void deleteLikeStoreByCustomerBas_CstmrNoAndStore_StoreNo(Integer customerNo, Integer storeNo);
}
