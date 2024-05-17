package com.kkosoonnae.config;

import com.kkosoonnae.jpa.entity.Store;
import com.kkosoonnae.jpa.repository.StoreRepository;
import com.kkosoonnae.map.dto.StoreCsvData;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CsvScheduleWriter implements ItemWriter<StoreCsvData> {

    private final StoreRepository storeRepository;
    @Override
    public void write(Chunk<? extends StoreCsvData> chunk) throws Exception {
        Chunk<Store> stores = new Chunk<>();

        chunk.forEach(StoreCsvData->{
            Store store = Store.of(StoreCsvData.getName(), StoreCsvData.getPhone(),StoreCsvData.getAddr());
        });
        storeRepository.saveAll(stores);
    }
}
