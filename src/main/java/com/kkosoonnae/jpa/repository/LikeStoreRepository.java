package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.CustomerBas;
import com.kkosoonnae.jpa.entity.LikeStore;
import com.kkosoonnae.jpa.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeStoreRepository extends JpaRepository<LikeStore,Integer> {
    List<LikeStore> countLikeStoreByStoreStoreNo (Integer customerId);

    boolean existsLikeStoreByCustomerBas_CstmrNoAndStore_StoreNo(Integer customerNo, Integer storeNo);
    @Transactional
    @Modifying
    void deleteLikeStoreByCustomerBas_CstmrNoAndStore_StoreNo(Integer customerNo, Integer storeNo);
}
