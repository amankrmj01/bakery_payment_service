package com.shah_s.bakery_payment_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableDiscoveryClient
@EnableFeignClients
public class BakeryPaymentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BakeryPaymentServiceApplication.class, args);
    }

}
