package com.kkosoonnae.batch;

import com.kkosoonnae.jpa.entity.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.Statement;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {
    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
//    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;
    @Autowired
    public JobCompletionNotificationListener(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            try {
                Statement statement = dataSource.getConnection().createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT storeName, address, phone FROM store");

                while (resultSet.next()) {
                    System.out.println("Found: " +
                            resultSet.getString("storeName") + ", " +
                            resultSet.getString("address") + ", " +
                            resultSet.getString("phone"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
}