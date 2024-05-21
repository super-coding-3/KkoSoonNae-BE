package com.kkosoonnae.batch;

import com.kkosoonnae.jpa.entity.Store;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfiguration {
    @Bean
    public FlatFileItemReader<Store> reader(){
        System.out.println("reader 실행 (파일을 읽는 부분)");
        return new FlatFileItemReaderBuilder<Store>()
                .name("storeItemReader")
                .resource(new ClassPathResource("data.csv"))
                .encoding("UTF-8")
                .delimited()
                .delimiter(",")
                .quoteCharacter('"')
                .names(new String[]{"storeName","phone","address"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Store>(){{
                    setTargetType(Store.class);
                }})
                .linesToSkip(1)
                .build();
    }

    @Bean
    public StoreItemProcessor processor() {
        System.out.println("processor 실행");
        return new StoreItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Store> writer(DataSource dataSource){
        System.out.println("writer 실행");
        return new JdbcBatchItemWriterBuilder<Store>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO store (STORE_NAME, CONTENT, ZIP_CODE, ADDRESS, ADDRESS_DTL, PHONE, STORE_OPER_DT, ROAD_ADDRESS) " +
                        "VALUES (:storeName, :content, :zipCode, :address, :addressDtl, :phone, :storeOperDt, :roadAddress)")
                .dataSource(dataSource)
                .build();
    }
    @Bean
    public Job importUserJob(JobRepository jobRepository,
                             JobCompletionNotificationListener listener,
                             Step step1){
        System.out.println("importUserJob");
        return new JobBuilder("importUserJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager,
                      JdbcBatchItemWriter<Store> writer)
    {
        System.out.println("Step1");
        return new StepBuilder("step1", jobRepository)
                .<Store, Store> chunk(10, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

}
