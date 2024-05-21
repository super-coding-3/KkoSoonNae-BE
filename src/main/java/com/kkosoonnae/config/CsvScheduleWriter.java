//package com.kkosoonnae.config;
//
//import com.kkosoonnae.jpa.entity.Store;
//import com.kkosoonnae.jpa.repository.StoreRepository;
//import com.kkosoonnae.map.dto.StoreCsvData;
//import jakarta.persistence.EntityManager;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.item.Chunk;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//@Component
//@Slf4j
//public class CsvScheduleWriter implements ItemWriter<StoreCsvData> {
//
//    private final StoreRepository storeRepository;
//    private final EntityManager entityManager;
//
//    @Autowired
//    public CsvScheduleWriter(StoreRepository storeRepository, EntityManager entityManager) {
//        this.storeRepository = storeRepository;
//        this.entityManager = entityManager;
//    }
//    @Override
//    @Transactional
//    public void write(Chunk<? extends StoreCsvData> chunk) throws Exception {
//        Chunk<Store> stores = new Chunk<>();
//        System.out.println("writer");
//        chunk.forEach(StoreCsvData->{
//            Store store = Store.of(StoreCsvData.getName(), StoreCsvData.getPhone(),StoreCsvData.getAddr());
//            stores.add(store);
//        });
//        storeRepository.saveAll(stores);
//        entityManager.flush();
//        System.out.println("데이터 저장 함");
//    }
//}
