package com.kkosoonnae;

import com.kkosoonnae.jpa.entity.CustomerBas;
import com.kkosoonnae.user.reservation.controller.ReservationController;
import com.kkosoonnae.user.reservation.dto.ReservationRequest;
import com.kkosoonnae.user.reservation.service.RedisLockService;
import com.kkosoonnae.user.reservation.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest(classes = KkosoonnaeApplicationTests.class)
class KkosoonnaeApplicationTests {

    @Test
    void contextLoads() {
    }

    @Mock
    private CustomerBas customerBas;


    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @WithMockUser(username = "test1")
    public void testConcurrentReservations() throws InterruptedException, ExecutionException {
        int threadCount = 10; // 동시 요청할 쓰레드 수
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        List<CompletableFuture<Void>> futures = new ArrayList<>();


        for (int i = 0; i < threadCount; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    ReservationRequest request = new ReservationRequest();
                    request.setReservationDate("2024-06-25");
                    request.setReservationTime("14:00");
                    request.setStoreNumber(1);
                    // 기타 필요한 요청 데이터 설정

                    reservationService.makeReservation(request);
                } catch (Exception e) {
                    // 예외 처리 (예: 이미 예약된 경우)
                    System.out.println(e.getMessage());
                }
            }, executor);
            futures.add(future);
        }

        // 모든 작업이 완료될 때까지 기다림
        for (CompletableFuture<Void> future : futures) {
            future.get();
        }

        executor.shutdown();
    }

}
