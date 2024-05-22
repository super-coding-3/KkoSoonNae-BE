//package com.kkosoonnae.config;
//
//import com.kkosoonnae.jpa.entity.Store;
//import com.kkosoonnae.map.dto.StoreCsvData;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.FileSystemResource;
//
//@Configuration
//@RequiredArgsConstructor
//public class CsvReader {
//    private String shopCsv="src/main/resources/data.csv";
//
//    @Bean
//    public FlatFileItemReader<StoreCsvData> csvScheduleReader(){
//        //파일 경로 지정 및 인코딩
//        FlatFileItemReader<StoreCsvData> flatFileItemReader = new FlatFileItemReader<>();
//        flatFileItemReader.setResource(new FileSystemResource(shopCsv));
//        flatFileItemReader.setEncoding("UTF-8");
//        System.out.println("reader");
//        //읽어온 파일을 한 줄씩 읽기
//        DefaultLineMapper<StoreCsvData> defaultLineMapper = new DefaultLineMapper<>();
//
//        //따로 설정하지 않으면 기본값은 ","
//        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
//
//        //필드 설정
//        delimitedLineTokenizer.setNames(StoreCsvData.getFieldNames().toArray(String[]::new));
//        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
//
//        // 매칭할 class 타입 지정(필드 지정)
//        BeanWrapperFieldSetMapper<StoreCsvData> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
//        beanWrapperFieldSetMapper.setTargetType(StoreCsvData.class);
//
//        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
//        flatFileItemReader.setLineMapper(defaultLineMapper);
//        System.out.println(flatFileItemReader);
//        return flatFileItemReader;
//    }
//}
