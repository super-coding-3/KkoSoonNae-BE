package com.kkosoonnae.batch;

import com.kkosoonnae.jpa.entity.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class StoreItemProcessor implements ItemProcessor<Store, Store> {

    private static final Logger log = LoggerFactory.getLogger(StoreItemProcessor.class);
    @Override
    public Store process(final Store store) throws Exception {

        System.out.println("process");
       final String storeName = store.getStoreName().trim();
       final String address = store.getAddress().trim();
       final String phone = store.getPhone().trim();

       final Store transformedStore = new Store(storeName, address, phone);

        log.info("input: "+transformedStore);

        return transformedStore;
    }
}
