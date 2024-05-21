//package com.kkosoonnae.config;
//
//import com.kkosoonnae.jpa.entity.Store;
//import com.kkosoonnae.map.dto.StoreCsvData;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//
//import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
//import org.springframework.batch.item.database.JdbcBatchItemWriter;
//import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//
//@Slf4j
//@Configuration
//@RequiredArgsConstructor
//public class SimpleJobConfiguration  {
//
//
//    private final CsvReader csvReader;
//    private final CsvScheduleWriter csvScheduleWriter;
//
////    private static final int chunkSize = 1000;
//    @Bean
//    public Job shopDataLoadJob(JobRepository jobRepository, Step shopDataLoadStep) {
//       System.out.println("job 시작");
//        return new JobBuilder("shopInformationLoadJob", jobRepository)
//                .start(shopDataLoadStep) // 스텝 실행
//                .build();
//        // 필요 시 listener 추가 가능
//    }
//
//
//    @Bean
//    public Step shopDataLoadStep(
//            JobRepository jobRepository,
//            PlatformTransactionManager platformTransactionManager
//    ) {
//        System.out.println("step 시작");
//        return new StepBuilder("shopDataLoadStep", jobRepository)
//                .<StoreCsvData, StoreCsvData>chunk(100, platformTransactionManager)
//                .reader(csvReader.csvScheduleReader())
//                .writer(csvScheduleWriter)
//                .build();
//    }
//
//}
