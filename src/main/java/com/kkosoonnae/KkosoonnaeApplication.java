package com.kkosoonnae;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableBatchProcessing
@EnableScheduling
public class KkosoonnaeApplication {
    public static void main(String[] args) {

        SpringApplication.run(KkosoonnaeApplication.class, args);
    }

}
